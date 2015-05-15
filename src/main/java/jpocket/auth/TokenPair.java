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
package jpocket.auth;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class TokenPair implements Serializable
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization
     */
    private static final long serialVersionUID = -1481491237603730477L;

    /**
     * 
     */
    private final String key;
    
    /**
     * 
     */
    private final String value;

    public TokenPair(String key, String value)
    {
        this.key = checkNotNull(key);
        this.value = checkNotNull(value);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass())
        {
            return false;
        }

        TokenPair other = (TokenPair) obj;
        return Objects.equal(this.getKey(), other.getKey()) && 
               Objects.equal(this.getValue(), this.getValue());
    }

    @Override
    public int hashCode()
    {
        return key.hashCode() ^ (value.hashCode() << 1);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("key", this.getKey())
                .add("value", getValue())
                .toString();
    }

    /**
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @return the value
     */
    public String getValue()
    {
        return value;
    }
}
