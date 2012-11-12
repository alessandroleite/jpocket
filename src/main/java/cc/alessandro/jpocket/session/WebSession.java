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