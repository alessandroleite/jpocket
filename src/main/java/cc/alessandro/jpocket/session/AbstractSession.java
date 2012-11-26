/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cc.alessandro.jpocket.session;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

public abstract class AbstractSession implements Session {
	
	private transient final Log log = LogFactory.getLog(this.getClass().getName());

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -1155983850918975327L;

	private static final String API_SERVER = "https://getpocket.com/v3/";

	/** How long connections are kept alive. */
	private static final int KEEP_ALIVE_DURATION_SECS = 20;

	/** How often the monitoring thread checks for connections to close. */
	private static final int KEEP_ALIVE_MONITOR_INTERVAL_SECS = 5;

	/** The default timeout for client connections. */
	private static final int DEFAULT_TIMEOUT_MILLIS = 1000 * 30;

	private transient HttpClient client = null;

	private final String apiKey;

	public AbstractSession(String apiKey) {
		checkArgument(!isNullOrEmpty(apiKey));
		this.apiKey = apiKey;
	}

	@Override
	public void sign(Request request) {
		if (this.getAccessToken() != null)
			request.addParam("access_token", this.getAccessToken().getValue());
	}

	public synchronized HttpClient getHttpClient() {
		if (client == null) {

			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					DEFAULT_TIMEOUT_MILLIS);
			HttpConnectionParams.setSoTimeout(httpParams,
					DEFAULT_TIMEOUT_MILLIS);
			HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
			HttpProtocolParams.setUserAgent(httpParams, "j-pocket-api/1.0");

			client = new DefaultHttpClient(ClientConnectionManager.instance(),
					httpParams) {
				@Override
				protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
					return new DefaultConnectionKeepAliveStrategy();
				}
			};
		}
		return client;
	}

	/**
	 * {@inheritDoc} <br/>
	 * <br/>
	 * The default implementation is <code>null</code>.
	 */
	@Override
	public ProxyInfo getProxyInfo() {
		return null;
	}

	/**
	 * {@inheritDoc} <br/>
	 * <br/>
	 * The default implementation always sets a 30 second timeout.
	 */
	@Override
	public void setRequestTimeout(HttpUriRequest request) {
		HttpParams reqParams = request.getParams();
		HttpConnectionParams.setSoTimeout(reqParams, DEFAULT_TIMEOUT_MILLIS);
		HttpConnectionParams.setConnectionTimeout(reqParams,
				DEFAULT_TIMEOUT_MILLIS);
	}

	@Override
	public String getAPIServer() {
		return API_SERVER;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLinked() {
		return this.getAccessToken() != null;
	}

	protected Log getLog() {
		return this.log;
	}

	private static class ClientConnectionManager extends PoolingClientConnectionManager {

		public ClientConnectionManager(SchemeRegistry schemeRegistry) {
			super(schemeRegistry);
		}

		@Override
		public ClientConnectionRequest requestConnection(HttpRoute route,
				Object state) {
			IdleConnectionCloserThread.ensureRunning(this,
					KEEP_ALIVE_DURATION_SECS, KEEP_ALIVE_MONITOR_INTERVAL_SECS);
			return super.requestConnection(route, state);
		}

		public static ClientConnectionManager instance() {
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
					.getSocketFactory()));
			schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory
					.getSocketFactory()));

			return new ClientConnectionManager(schemeRegistry);
		}
	}

	private static class IdleConnectionCloserThread extends Thread {
		private final ClientConnectionManager manager;
		private final int idleTimeoutSeconds;
		private final int checkIntervalMs;
		private static IdleConnectionCloserThread thread = null;

		public IdleConnectionCloserThread(ClientConnectionManager manager,
				int idleTimeoutSeconds, int checkIntervalSeconds) {
			super();
			this.manager = manager;
			this.idleTimeoutSeconds = idleTimeoutSeconds;
			this.checkIntervalMs = checkIntervalSeconds * 1000;
		}

		public static synchronized void ensureRunning(
				ClientConnectionManager manager, int idleTimeoutSeconds,
				int checkIntervalSeconds) {
			if (thread == null) {
				thread = new IdleConnectionCloserThread(manager,
						idleTimeoutSeconds, checkIntervalSeconds);
				thread.start();
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					synchronized (this) {
						wait(checkIntervalMs);
					}
					manager.closeExpiredConnections();
					manager.closeIdleConnections(idleTimeoutSeconds,
							TimeUnit.SECONDS);
					synchronized (IdleConnectionCloserThread.class) {
						if (manager.getTotalStats().getAvailable() == 0) {
							thread = null;
							return;
						}
					}
				}
			} catch (InterruptedException e) {
				thread = null;
			}
		}
	}

	private static class DefaultConnectionKeepAliveStrategy implements
			ConnectionKeepAliveStrategy {
		@Override
		public long getKeepAliveDuration(HttpResponse response,
				HttpContext context) {
			long timeout = KEEP_ALIVE_DURATION_SECS * 1000;

			HeaderElementIterator i = new BasicHeaderElementIterator(
					response.headerIterator(HTTP.CONN_KEEP_ALIVE));

			while (i.hasNext()) {
				HeaderElement element = i.nextElement();
				String name = element.getName();
				String value = element.getValue();
				if (value != null && name.equalsIgnoreCase("timeout")) {
					try {
						timeout = Math.min(timeout,
								Long.parseLong(value) * 1000);
					} catch (NumberFormatException e) {
					}
				}
			}
			return timeout;
		}
	}
}
