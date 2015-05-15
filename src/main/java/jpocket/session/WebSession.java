/**
 * Copyright (C) 2015  the original authors and contributors
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
package jpocket.session;

import static jpocket.session.Request.ParamType.REDIRECT_URI;
import static jpocket.session.Request.Type.newType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jpocket.auth.AccessToken;
import jpocket.auth.RequestToken;
import jpocket.config.Application;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSession implements Session
{

    private transient final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * Serial code version <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1155983850918975327L;

    private static final String API_SERVER = "https://getpocket.com/v3/";

    /** The default timeout for client connections. */
    private static final int DEFAULT_TIMEOUT_MILLIS = 1000 * 30;

    private transient HttpClient client = null;

    private final Application application;

    public WebSession(Application application) throws IOException
    {
        this.application = application;
        
        if (application.getCredential().getAccessToken() == null || application.getCredential().getAccessToken().getValue() == null)
        {
            Request request = new Request("/oauth/request", this, newType(REDIRECT_URI, application.getUrl()));
            Response response = new RestUtility().execute(request);
            
            RequestToken requestToken = new RequestToken(response.getCode(), application.getUrl());
            
            HttpGet get = new HttpGet(requestToken.getAuthorizationURL());
            getHttpClient().execute(get);
            
            request = newRequest("oauth/authorize");
            request.addParam("code", requestToken.getValue());
            
            AccessToken accessToken = new RestUtility().execute(request, AccessToken.class);
            
            application.getCredential().setRequestToken(requestToken).setAccessToken(accessToken);
        }
    }

    @Override
    public Request newRequest(final String uri)
    {
        Request request = new Request(uri, this);
        request.addParam("access_token", application.getCredential().getAccessToken().getValue());
        return request;
    }
    
    @Override
    public synchronized HttpClient getHttpClient()
    {
        if (client == null)
        {
            client = HttpClients.custom().setConnectionTimeToLive(DEFAULT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).setUserAgent("jpocket-client/1.0").build();
        }

        return client;
    }

    /**
     * {@inheritDoc} <br/>
     * <br/>
     * The default implementation is <code>null</code>.
     */
    @Override
    public ProxyInfo getProxyInfo()
    {
        return null;
    }

    /**
     * {@inheritDoc} <br/>
     * <br/>
     * The default implementation always sets a 30 second timeout.
     */
    @Override
    public void setRequestTimeout(HttpUriRequest request)
    {
    }

    @Override
    public String getAPIServer()
    {
        return API_SERVER;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey()
    {
        return application.getCredential().getConsumerKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLinked()
    {
        return this.application.getCredential().getAccessToken() != null;
    }

    protected Logger getLog()
    {
        return this.LOG;
    }

    public static Session createSession(Application application) throws IOException
    {
        return new WebSession(application);
    }
}
