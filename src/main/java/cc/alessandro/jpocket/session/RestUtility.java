package cc.alessandro.jpocket.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import cc.alessandro.jpocket.TypeData;
import cc.alessandro.jpocket.exception.PocketException;
import cc.alessandro.jpocket.session.Request.Type;

import com.google.gson.Gson;

public class RestUtility {

	private static final String DEFAULT_CHARACTER_SET = "UTF-8";

	protected HttpPost createHttpRequest(Request request) throws IOException{

		final HttpPost post = new HttpPost(request.getSession().getAPIServer() + request.getUri());

		addHeaders(post, request);
		addParameters(post, request);

		return post;
	}

	private void addParameters(HttpPost post, Request request) throws IOException{
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		for (Type param : request.getParameters()) {
			params.add(new BasicNameValuePair(param.getName(), param.getValue()));
		}
		
		post.setEntity(new UrlEncodedFormEntity(params, DEFAULT_CHARACTER_SET));
	}

	private void addHeaders(HttpPost post, Request request) {
		post.addHeader("Content-Type", String.format("%s;%s",
				TypeData.FORM_URL_ENCODED.mimeType(), DEFAULT_CHARACTER_SET));

		post.addHeader("X-Accept", TypeData.JSON.mimeType());

		for (Type type : request.getHeaders()) {
			post.addHeader(type.getName(), type.getValue());
		}
	}

	protected String asString(HttpResponse response) throws PocketException,
			IOException {

		int status = response.getStatusLine().getStatusCode();

		if (!(status == 200)) {
			throwExceptionBadRequest(response);
		}

		StringBuilder result = new StringBuilder();

		HttpEntity ent = response.getEntity();
		if (ent != null) {
			result.append(IOUtils.toString(ent.getContent()));
		}
		return result.toString();
	}

	protected void throwExceptionBadRequest(HttpResponse response)
			throws PocketException {
		
		throw new PocketException(response.getStatusLine().getStatusCode(),
				Integer.parseInt(response.getHeaders("X-Error-Code")[0]
						.getValue()),
				response.getHeaders("X-Error")[0].getValue());
	}

	public Response execute(Request request) throws IOException {
		request.sign();
		
		final HttpPost post = this.createHttpRequest(request);
		HttpResponse response =  request.getSession().getHttpClient().execute(post);
		String json = asString(response);
		
		Response resp = this.parserJSON(json);
		resp.setJson(json);
		return resp;
	}

	private Response parserJSON(String json) {
		return new Gson().fromJson(json, Response.class);
	}
}