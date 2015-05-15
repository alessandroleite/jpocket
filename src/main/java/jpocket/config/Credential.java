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

import com.google.common.base.MoreObjects;

import jpocket.auth.AccessToken;
import jpocket.auth.RequestToken;

public class Credential
{
    private String consumerKey;
    private RequestToken requestToken;
    private AccessToken accessToken;
    /**
     * @return the consumerKey
     */
    public String getConsumerKey()
    {
        return consumerKey;
    }
    /**
     * @param consumerKey the consumerKey to set
     */
    public Credential setConsumerKey(String consumerKey)
    {
        this.consumerKey = consumerKey;
        return this;
    }
    /**
     * @return the requestToken
     */
    public RequestToken getRequestToken()
    {
        return requestToken;
    }
    /**
     * @param requestToken the requestToken to set
     */
    public Credential setRequestToken(RequestToken requestToken)
    {
        this.requestToken = requestToken;
        return this;
    }
    /**
     * @return the accessToken
     */
    public AccessToken getAccessToken()
    {
        return accessToken;
    }
    /**
     * @param accessToken the accessToken to set
     */
    public Credential setAccessToken(AccessToken accessToken)
    {
        this.accessToken = accessToken;
        return this;
    }
    
    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("consumer_key", getConsumerKey())
                .add("request_token", getRequestToken())
                .add("access_token", getAccessToken())
                .omitNullValues()
                .toString();
    }
}
