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

import static com.google.common.base.Preconditions.checkNotNull;
import cc.alessandro.jpocket.auth.AccessToken;
import cc.alessandro.jpocket.auth.RequestToken;

public class WebSession extends AbstractSession {
	
	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = 3503299939105775626L;
	
	private RequestToken requestToken;
	private AccessToken accessToken;

	public WebSession(String apiKey) {
		super(apiKey);
	}
	
	public WebSession(AccessToken accessToken){
		this(accessToken.getRequestToken().getKey());
		
		this.requestToken = new RequestToken(accessToken.getRequestToken().getKey(), accessToken.getRequestToken().getValue(), 
				accessToken.getRequestToken().getCallbackURL(), this);
		
		this.accessToken = accessToken;
		this.accessToken.setRequestToken(this.requestToken);
	}
	
	@Override
	public void setRequestToken(RequestToken code) {
		this.requestToken = checkNotNull(code);
	}
	
	@Override
	public RequestToken getRequestToken() {
		return this.requestToken;
	}

	@Override
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = checkNotNull(accessToken);
	}

	@Override
	public AccessToken getAccessToken() {
		return this.accessToken;
	}
}