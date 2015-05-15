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

import com.google.common.base.Optional;

public enum State
{
    /**
     * 
     */
    NORMAL(0),
    
    /**
     * Item archived
     */
    ARCHIVED(1), 
    
    /**
     * Item deleted
     */
    DELETED(2);

    private final Integer id;

    private State(Integer id)
    {
        this.id = id;
    }

    public static Optional<State> newState(Integer id)
    {
        Optional<State> result = Optional.absent();
        
        for (State state : State.values())
        {
            if (state.getId().equals(id))
            {
                result = Optional.of(state);
                break;
            }
        }
        
        return result;
    }

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }
}
