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
package cc.alessandro.jpocket.auth;

import cc.alessandro.jpocket.session.Session;

public final class RequestToken extends TokenPair {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 7853975135201709392L;

	private static final String PARAM_NAME = "request_token";

	private final Session session;
	private final String callbackURL;

	public RequestToken(String key, String value, String callbackURL, Session session) {
		super(key, value);
		this.callbackURL = callbackURL;
		this.session = session;
		this.session.setRequestToken(this);
	}

	public static RequestToken valueOf(String value, String callbackURL, Session session) {
		return new RequestToken(PARAM_NAME, value, callbackURL, session);
	}

	final static String AUTHORIZATION_URL = "https://getpocket.com/auth/authorize?request_token=%s&redirect_uri=%s";
	
	public String getAuthorizationURL() {
		return String.format(AUTHORIZATION_URL, this.getValue(), callbackURL);
	}

	public String getCallbackURL() {
		return callbackURL;
	}
	
	public Session getSession() {
		return session;
	}
}