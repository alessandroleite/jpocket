package cc.alessandro.jpocket.exception;

@SuppressWarnings("serial")
public class PocketException extends RuntimeException {

	/** HTTP Status Code */
	private final int status;
	
	/** X-Error-Code */
	private final int errorCode;
	
	/** X-Error description */
	private final String message;
	
	public PocketException(int status, int code, String message) {
		super(message);
		
		this.status = status;
		this.errorCode = code;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}