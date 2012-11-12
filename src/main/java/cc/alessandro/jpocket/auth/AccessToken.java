package cc.alessandro.jpocket.auth;

public class AccessToken extends TokenPair {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2824725154966897312L;
	
	private RequestToken requestToken;
	private String username;

	public AccessToken(String key, String value) {
		super(key, value);
	}

	public AccessToken(RequestToken requestToken, String value, String username) {
		this("access_token", value);
		
		this.requestToken = requestToken;
		this.username = username;
	}

	/**
	 * @return the requestToken
	 */
	public RequestToken getRequestToken() {
		return requestToken;
	}

	/**
	 * @param requestToken the requestToken to set
	 */
	public void setRequestToken(RequestToken requestToken) {
		this.requestToken = requestToken;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}