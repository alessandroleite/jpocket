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

	public String getAuthenticationURL() {
		return String.format(
				"%s/oauth/authorize?request_token=%s&redirect_uri=%s",
				this.session.getAPIServer(), this.getValue(), callbackURL);
	}
	
	public String getCallbackURL() {
		return callbackURL;
	}
}