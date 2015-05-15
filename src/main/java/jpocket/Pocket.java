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
package jpocket;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import jpocket.config.Application;
import jpocket.config.Credential;
import jpocket.filters.Parameter;
import jpocket.session.Request;
import jpocket.session.RestUtility;
import jpocket.session.Session;
import jpocket.session.WebSession;

public class Pocket
{
    private final AtomicReference<Session> session;
    private final Application application;
    
    public Pocket (Application application)
    {
        this.application = checkNotNull(application);
        session = new AtomicReference<Session>();
    }

    public Pocket(String apiKey, String callbackURL)
    {
        this(new Application().setUrl(callbackURL).setCredential(new Credential().setConsumerKey(apiKey)));
    }

    protected Session authenticate() throws IOException
    {
        if (session.get() == null)
        {
            session.compareAndSet(null, WebSession.createSession(application));
        }
        
        return session.get();
    }

    public Items get(Parameter filter) throws IOException
    {
        Request request = authenticate().newRequest("/get");
        
        if (filter != null)
        {
            request.addParams(filter.asType());
        }

        return new RestUtility().execute(request, Items.class);
    }
}
