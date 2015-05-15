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
import static com.google.common.collect.Lists.newCopyOnWriteArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Optional;

public class Items implements Iterable<Article>
{
    private final List<Article> items = newCopyOnWriteArrayList();
    
    private final State state;
    
    public Items(State state)
    {
        this.state = checkNotNull(state);
    }
    
    public Items()
    {
        this(State.NORMAL);
    }

    @Override
    public Iterator<Article> iterator()
    {
        return this.get().iterator();
    }

    public List<Article> get()
    {
        return Collections.unmodifiableList(items);
    }

    public boolean add(Article status)
    {
        boolean result = false;
        
        if (status != null)
        {
            result = items.add(status);
        }
        
        return result;
    }
    
    public int size()
    {
        return this.items.size();
    }
    
    public boolean isEmpty()
    {
        return this.items.isEmpty();
    }
    
    public synchronized Optional<Article> first()
    {
        return isEmpty() ? Optional.<Article>absent() : get(0);
    }
    
    public synchronized Optional<Article> last()
    {
        return isEmpty() ? Optional.<Article>absent() : get(size() - 1);
    }
    
    public synchronized Optional<Article> get(int index)
    {
        return isEmpty() ? Optional.<Article>absent() : Optional.of(items.get(index));
    }

    /**
     * @return the state
     */
    public State getState()
    {
        return state;
    }
}
