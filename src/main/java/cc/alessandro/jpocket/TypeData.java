package cc.alessandro.jpocket;

public enum TypeData {

	/** JSON format */
	JSON("application/json"),

	/** Form URL Encoded */
	FORM_URL_ENCODED("application/x-www-form-urlencoded");

	private final String mimeType;

	private TypeData(String mime) {
		this.mimeType = mime;
	}

	public String mimeType() {
		return mimeType;
	}
}