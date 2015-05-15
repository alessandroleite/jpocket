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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;

import static com.google.common.collect.Maps.*;

public class Images implements Iterable<Image>
{
    private final Map<Long, Image> images = newHashMap();
    private Item parent;
    
    public Images()
    {
        super();
    }
    
    public Images(Item owner)
    {
        this.parent = owner;
    }

    public Image add(Image image)
    {
        Image previous = null;

        if (image != null)
        {
            image.setParent(parent);
            previous = images.put(image.getId(), image);
        }

        return previous;
    }

    public List<Image> addAll(Iterable<Image> images)
    {
        List<Image> previous = new ArrayList<Image>();

        for (Image image : images)
        {
            previous.add(add(image));
        }

        return previous;
    }

    @Override
    public Iterator<Image> iterator()
    {
        return get().iterator();
    }

    public List<Image> get()
    {
        return Collections.unmodifiableList(new ArrayList<Image>(images.values()));
    }

    public boolean isEmpty()
    {
        return images.isEmpty();
    }

    public int size()
    {
        return images.size();
    }

    public Optional<Image> first()
    {
        final List<Image> images = get();

        return images.isEmpty() ? Optional.<Image> absent() : Optional.of(images.get(1));
    }
}
