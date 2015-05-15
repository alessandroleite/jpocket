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

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class Response implements Serializable
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = -8855916179272160909L;

    private final String code;
    private final String state;
    private final String json;

    public Response(String code, String state, String json)
    {
        this.code = code;
        this.state = state;
        this.json = json;
    }
    
    public static Response newResponse(String code, String state, String json)
    {
        return new Response(code, state, json);
    }

    /**
     * @return the code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }


    /**
     * @return the json
     */
    public String getJSON()
    {
        return json;
    }
    
    
    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("code", getCode()).add("state", getState()).add("JSON", getJSON()).omitNullValues().toString();
    }
}
