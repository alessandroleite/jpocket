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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Request implements Serializable
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization
     */
    private static final long serialVersionUID = 2541034963037622646L;

    private final Map<String, Type> parameters = new HashMap<String, Request.Type>();
    
    private final List<Type> headers = new ArrayList<Request.Type>();

    private final String uri;
    private final Session session;

    public Request(String uri, Session session)
    {
        this.uri = uri;
        this.session = session;
        this.addParam("consumer_key", session.getApiKey());
    }
    
    public Request(String uri, Session session, Type ... params)
    {
        this(uri, session);
        
        for (Type param: params)
        {
            this.addParam(param);
        }
    }

    public Type addParam(Type param)
    {
        return this.parameters.put(param.getName(), param);
    }

    public Type addParam(String name, String value)
    {
        return this.addParam(new Type(name, value));
    }

    public Type addParam(ParamType type, String value)
    {
        return this.addParam(type.name().toLowerCase(), value);
    }

    public void addParams(List<Type> params)
    {
        if (params != null)
        {
            for (Type param : params)
            {
                this.addParam(param);
            }
        }
    }

    public Type removeParam(String name)
    {
        return this.parameters.remove(name);
    }

    public Type removeParam(Type param)
    {
        return this.removeParam(param.getName());
    }

    public Type getParameter(String name)
    {
        return this.parameters.get(name);
    }

    public Collection<Type> getParameters()
    {
        return Collections.unmodifiableCollection(this.parameters.values());
    }

    public boolean addHeader(String name, String value)
    {
        return this.addHeader(new Type(name, value));
    }

    public boolean addHeader(Type type)
    {
        return this.headers.add(checkNotNull(type));
    }

    public List<Type> getHeaders()
    {
        return Collections.unmodifiableList(headers);
    }

    public String getUri()
    {
        return uri;
    }

    public static enum ParamType
    {
        REDIRECT_URI;
    }

    public final static class Type implements Serializable
    {
        /**
         * Serial code version <code>serialVersionUID</code> for serialization.
         */
        private static final long serialVersionUID = 3311061969505958939L;

        private final String name;
        private final String value;

        public Type(String name, String value)
        {
            this.name = checkNotNull(name);
            this.value = value;
        }
        
        public static Type newType(ParamType type, String value)
        {
            return new Type(type.name().toLowerCase(), value);
        }

        /**
         * @return the name
         */
        public String getName()
        {
            return name;
        }

        /**
         * @return the value
         */
        public String getValue()
        {
            return value;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }

            if (obj == null || obj.getClass() != this.getClass())
            {
                return false;
            }

            return Objects.equal(this.getName(), ((Type) obj).getName());
        }

        @Override
        public int hashCode()
        {
            return Objects.hashCode(this.getName()) * 31;
        };

        @Override
        public String toString()
        {
            return MoreObjects.toStringHelper(this).add("name", getName()).add("value", getValue()).omitNullValues().toString();
        }
    }

    public Session getSession()
    {
        return session;
    }
}
