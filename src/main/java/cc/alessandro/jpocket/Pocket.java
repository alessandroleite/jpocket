package cc.alessandro.jpocket;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.IOException;
import java.util.List;

import org.apache.http.annotation.NotThreadSafe;

import cc.alessandro.jpocket.auth.AccessToken;
import cc.alessandro.jpocket.auth.RequestToken;
import cc.alessandro.jpocket.exception.PocketException;
import cc.alessandro.jpocket.filters.Parameter;
import cc.alessandro.jpocket.session.Request;
import cc.alessandro.jpocket.session.Request.ParamType;
import cc.alessandro.jpocket.session.Response;
import cc.alessandro.jpocket.session.RestUtility;
import cc.alessandro.jpocket.session.Session;
import cc.alessandro.jpocket.session.WebSession;

@NotThreadSafe
public class Pocket {

	private final Session session;
	private final String callbackURL;

	public Pocket(String apiKey, String callbackURL) {
		this(new WebSession(apiKey), callbackURL);
	}

	protected Pocket(Session session, String callbackURL) {
		this.session = checkNotNull(session);
		this.callbackURL = checkNotNull(callbackURL);
	}

	public RequestToken requestToken() throws PocketException, IOException {

		Request request = new Request("/oauth/request", session);
		request.addParam(ParamType.REDIRECT_URI, callbackURL);

		Response response = new RestUtility().execute(request);
		return RequestToken.valueOf(response.getCode(), callbackURL, session);
	}

	/**
	 * https://getpocket.com/v3/oauth/authorize
	 */
	public AccessToken authorized(String verifier) throws PocketException, IOException {
		checkArgument(!isNullOrEmpty(verifier));

		Request request = new Request("/oauth/authorize", session);
		request.addHeader("code", verifier);

		Response response = new RestUtility().execute(request);
		session.setAccessToken(new AccessToken(session.getRequestToken(), 
				response.getAccessToken(), response.getUsername()));
		
		return session.getAccessToken();
	}

	/**
	 * Add new url to pocket
	 * 
	 * @param url
	 *            Url, starting from http
	 * @param title
	 *            Personal title for url
	 */
	void add(String url, String title) 
	{
		assertThatIsLinked();
	}
	
	public List<Status> get(Parameter filter) throws IOException {
		assertThatIsLinked();
		
		Request request = new Request("/get", session);
		if (filter != null) {
			request.addParams(filter.asType());
		}
		
		Response response = new RestUtility().execute(request);
		return StatusFactory.parser(response);
	}

	private void assertThatIsLinked() {
		if (!session.isLinked())
			throw new PocketException(500, 999, "Unliked session!");
	}
}