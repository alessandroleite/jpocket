package cc.alessandro.jpocket.session;

import java.io.Serializable;

public class Response implements Serializable{
	
	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = 1293612143192917226L;
	
	private String code;
	private String state;
	private String accessToken;
	private String username;
	private String json;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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
	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}
	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}
}