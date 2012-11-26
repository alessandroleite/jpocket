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

import java.io.Serializable;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import cc.alessandro.jpocket.auth.AccessToken;
import cc.alessandro.jpocket.auth.RequestToken;

public interface Session extends Serializable{

	String getApiKey();

	/**
	 * OAuth signs the request with the currently-set tokens and secrets.
	 * 
	 * @param request an {@link Request}
	 */
	void sign(Request request);

	void setRequestToken(RequestToken code);

	void setAccessToken(AccessToken accessToken);

	RequestToken getRequestToken();

	AccessToken getAccessToken();

	ProxyInfo getProxyInfo();

	HttpClient getHttpClient();

	void setRequestTimeout(HttpUriRequest request);

	String getAPIServer();

	boolean isLinked();

	/**
	 * Describes a proxy.
	 */
	public static final class ProxyInfo {

		/** The address of the proxy. */
		private final String host;

		/** The port of the proxy, or -1 to use the default port. */
		private final int port;

		/**
		 * Creates a proxy info.
		 * 
		 * @param host
		 *            the host to use without a protocol (required).
		 * @param port
		 *            the port to use, or -1 for default port.
		 */
		public ProxyInfo(String host, int port) {
			checkArgument(isNullOrEmpty(host));

			this.host = host;
			this.port = port;
		}

		/**
		 * Creates a proxy info using the default port.
		 * 
		 * @param host
		 *            the host to use without a protocol (required).
		 */
		public ProxyInfo(String host) {
			this(host, -1);
		}

		/**
		 * @return the host
		 */
		public String getHost() {
			return host;
		}

		/**
		 * @return the port
		 */
		public int getPort() {
			return port;
		}

		@Override
		public String toString() {
			return String.format("ProxyInfo [host = %s, port = %s]", this.host,
					this.port);
		}
	}
}