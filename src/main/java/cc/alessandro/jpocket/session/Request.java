package cc.alessandro.jpocket.session;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

	private final Map<String, Type> parameters = new HashMap<String, Request.Type>();
	private final List<Type> headers = new ArrayList<Request.Type>();

	private final Integer mutex = new Integer(-1);

	private final String apiKey;
	private final String uri;

	private Session session;

	public Request(String uri, String key) {
		this.uri = uri;
		this.apiKey = key;
		this.addParam("consumer_key", key);
	}

	public Request(String uri, Session session) {
		this(uri, checkNotNull(session).getApiKey());
		this.session = session;
	}

	public Type addParam(Type param) {
		return this.parameters.put(param.getName(), param);
	}

	public Type addParam(String name, String value) {
		return this.addParam(new Type(name, value));
	}

	public Type addParam(ParamType type, String value) {
		return this.addParam(type.name().toLowerCase(), value);
	}

	public Type removeParam(String name) {
		return this.parameters.remove(name);
	}

	public Type removeParam(Type param) {
		return this.removeParam(param.getName());
	}
	
	public Type getParameter(String name) {
		return this.parameters.get(name);
	}

	public Collection<Type> getParameters() {
		return Collections.unmodifiableCollection(this.parameters.values());
	}

	public boolean addHeader(String name, String value) {
		return this.addHeader(new Type(name, value));
	}

	public boolean addHeader(Type type) {
		return this.headers.add(checkNotNull(type));
	}

	public List<Type> getHeaders() {
		return Collections.unmodifiableList(headers);
	}

	public Session getSession(boolean created) {

		synchronized (mutex) {
			if (created && session == null)
				session = new WebSession(apiKey);
		}
		return this.session;
	}

	public Session getSession() {
		return this.getSession(true);
	}

	public String getUri() {
		return uri;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void sign() {
		this.getSession().sign(this);
	}

	public static enum ParamType {
		REDIRECT_URI;
	}

	public final static class Type implements Serializable {

		/**
		 * Serial code version <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = -2322758321566274906L;

		private final String name;
		private final String value;

		public Type(String name, String value) {
			this.name = checkNotNull(name);
			this.value = value;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;

			if (!(obj instanceof Type))
				return false;

			return this.name.equalsIgnoreCase(((Type) obj).name);
		}

		@Override
		public int hashCode() {
			return this.getName().hashCode() * 17;
		};

		@Override
		public String toString() {
			return String.format("Name: %s, value: %s", this.getName(), this.getValue());
		}
	}
}