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
package cc.alessandro.jpocket;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.IOException;

import org.apache.http.annotation.NotThreadSafe;

import cc.alessandro.jpocket.auth.AccessToken;
import cc.alessandro.jpocket.auth.RequestToken;
import cc.alessandro.jpocket.exception.PocketException;
import cc.alessandro.jpocket.filters.Parameter;
import cc.alessandro.jpocket.session.Request;
import cc.alessandro.jpocket.session.Request.ParamType;
import cc.alessandro.jpocket.session.Response;
import cc.alessandro.jpocket.session.RestUtility;
import cc.alessandro.jpocket.session.Session;
import cc.alessandro.jpocket.session.WebSession;

@NotThreadSafe
public class Pocket {

	private Session session;
	private final String callbackURL;

	public Pocket(String apiKey, String callbackURL) {
		this(new WebSession(apiKey), callbackURL);
	}

	protected Pocket(Session session, String callbackURL) {
		this.session = checkNotNull(session);
		this.callbackURL = checkNotNull(callbackURL);
	}

	public RequestToken requestToken() throws PocketException, IOException {

		Request request = new Request("/oauth/request", session);
		request.addParam(ParamType.REDIRECT_URI, callbackURL);

		Response response = new RestUtility().execute(request);
		RequestToken requestToken = RequestToken.valueOf(response.getCode(), callbackURL, session);
		session.setRequestToken(requestToken);
		
		return requestToken;
	}

	/**
	 * https://getpocket.com/v3/oauth/authorize
	 * @param requestToken
	 * @return The {@link AccessToken}
	 */
	public AccessToken authorized(RequestToken requestToken) throws PocketException, IOException {
		checkArgument(!isNullOrEmpty(checkNotNull(requestToken).getValue()));
		this.synchronizeSession(requestToken);

		Request request = new Request("/oauth/authorize", session);
		request.addParam("code", requestToken.getValue());

		AccessToken accessToken = new RestUtility().execute(request, AccessToken.class);
		accessToken.setRequestToken(requestToken);
		session.setAccessToken(accessToken);
		
		return accessToken;
	}

	private void synchronizeSession(RequestToken requestToken) {
		if (this.session != requestToken.getSession()) {
			this.session = requestToken.getSession();
		}
	}

	/**
	 * Add new url to pocket
	 * 
	 * @param url
	 *            Url, starting from http
	 * @param title
	 *            Personal title for url
	 */
	void add(String url, String title) 
	{
		assertThatIsLinked();
	}
	
	public Statuses get(Parameter filter) throws IOException {
		assertThatIsLinked();
		
		Request request = new Request("/get", session);
		if (filter != null) {
			request.addParams(filter.asType());
		}
		
		return new RestUtility().execute(request, Statuses.class);
	}

	private void assertThatIsLinked() {
		if (!session.isLinked())
			throw new PocketException(500, 999, "Unliked session!");
	}
}