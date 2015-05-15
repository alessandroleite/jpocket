/**
 * Copyright (C) 2015  the original authors and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package jpocket.session;

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

import jpocket.TypeData;
import jpocket.exception.PocketException;
import jpocket.gson.GsonFactory;
import jpocket.session.Request.Type;
import static java.lang.Integer.*;

public class RestUtility
{

    private static final String DEFAULT_CHARACTER_SET = "UTF-8";

    protected HttpPost createHttpRequest(Request request) throws IOException
    {

        final HttpPost post = new HttpPost(request.getSession().getAPIServer() + request.getUri());

        addHeaders(post, request);
        addParameters(post, request);

        return post;
    }

    private void addParameters(HttpPost post, Request request) throws IOException
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        for (Type param : request.getParameters())
        {
            params.add(new BasicNameValuePair(param.getName(), param.getValue()));
        }

        post.setEntity(new UrlEncodedFormEntity(params, DEFAULT_CHARACTER_SET));
    }

    private void addHeaders(HttpPost post, Request request)
    {
        post.addHeader("Content-Type", String.format("%s;%s", TypeData.FORM_URL_ENCODED.mimeType(), DEFAULT_CHARACTER_SET));
        post.addHeader("X-Accept", TypeData.JSON.mimeType());

        for (Type type : request.getHeaders())
        {
            post.addHeader(type.getName(), type.getValue());
        }
    }

    protected void asString(HttpResponse response, StringBuilder buffer) throws PocketException, IOException
    {
        final int status = response.getStatusLine().getStatusCode();

        if (!(status == 200))
        {
            throwExceptionBadRequest(response);
        }

        HttpEntity ent = response.getEntity();
        
        if (ent != null)
        {
            buffer.append(IOUtils.toString(ent.getContent()));
        }
    }

    protected void throwExceptionBadRequest(HttpResponse response) throws PocketException
    {
        throw new PocketException(response.getStatusLine().getStatusCode(), 
                         parseInt(response.getHeaders("X-Error-Code")[0].getValue()),
                         response.getHeaders("X-Error")[0].getValue());
    }

    public Response execute(Request request) throws IOException
    {
        return this.execute(request, Response.class, new StringBuilder());
    }

    public <T> T execute(Request request, Class<T> jsonType) throws IOException
    {
        return this.execute(request, jsonType, new StringBuilder());
    }

    protected <T> T execute(Request request, Class<T> jsonType, StringBuilder buffer) throws IOException
    {
        final HttpPost post = this.createHttpRequest(request);
        
        try
        {
            HttpResponse response = request.getSession().getHttpClient().execute(post);
            asString(response, buffer);
            return GsonFactory.parser(buffer.toString(), jsonType);
        }
        finally
        {
            post.reset();
        }
    }
}
