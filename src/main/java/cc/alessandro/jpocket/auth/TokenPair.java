package cc.alessandro.jpocket.auth;

import java.io.Serializable;

import static com.google.common.base.Preconditions.*;

public class TokenPair implements Serializable {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3791752298882657471L;

	private final String key;

	private final String value;

	public TokenPair(String key, String value) {
		this.key = checkNotNull(key);
		this.value = checkNotNull(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (!(obj instanceof TokenPair))
			return false;

		TokenPair other = (TokenPair) obj;
		return this.key.equals(other.key) && value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return key.hashCode() ^ (value.hashCode() << 1);
	}

	@Override
	public String toString() {
		return "{key=\"" + key + "\", value=\"" + value + "\"}";
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}