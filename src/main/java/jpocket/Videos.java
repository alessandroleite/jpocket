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

import static com.google.common.collect.Maps.newHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Videos implements Iterable<Video>
{
    private final Map<Long, Video> videos = newHashMap();
    private Item parent;
    
    public Videos()
    {
        super();
    }
    
    public Videos (Item owner)
    {
        this.parent = owner;
    }

    public Video add(Video video)
    {
        Video previous = null;
        
        if (video != null)
        {
            video.setParent(parent);
            previous = videos.put(video.getId(), video);
        }
        
        return previous;
    }

    @Override
    public Iterator<Video> iterator()
    {
        return get().iterator();
    }
    
    public List<Video> get()
    {
        return Collections.unmodifiableList(new ArrayList<Video>(videos.values()));
    }

    public List<Video> addAll(Iterable<Video> videos)
    {
        List<Video> previous = new ArrayList<Video>();
        
        for (Video video: videos)
        {
            previous.add(add(video));
        }
        
        return previous;
    }

    public int size()
    {
        return videos.size();
    }
}
