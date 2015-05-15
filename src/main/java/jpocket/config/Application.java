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
package jpocket.config;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class Application implements Serializable
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = 2258276716914634620L;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private Credential credential;
    
    

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public Application setName(String name)
    {
        this.name = name;
        return this;
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url the url to set
     */
    public Application setUrl(String url)
    {
        this.url = url;
        return this;
    }

    /**
     * @return the credential
     */
    public Credential getCredential()
    {
        return credential;
    }

    /**
     * @param credential the credential to set
     */
    public Application setCredential(Credential credential)
    {
        this.credential = credential;
        return this;
    }
    
    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("name", getName())
                .add("callback_url", getUrl())
                .add("credential", getCredential())
                .omitNullValues()
                .toString();
    }
}
