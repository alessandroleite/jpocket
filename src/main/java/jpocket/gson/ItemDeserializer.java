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
package jpocket.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.joda.time.DateTime;

import jpocket.Image;
import jpocket.Article;
import jpocket.Video;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import static jpocket.State.*;

public class ItemDeserializer implements JsonDeserializer<Article>
{
    @Override
    public Article deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException
    {
        final JsonObject obj = (JsonObject) json; 
        final Article status = new Article();
        
        status.setAddedAt(getAsDatetime("time_added", obj))
              .setArticle(obj.get("is_article").getAsBoolean())
              .setExcerpt(obj.get("excerpt").getAsString())
              .setFavorite(obj.get("favorite").getAsBoolean())
              .setGivenUrl(obj.get("given_url").getAsString())
              .setId(obj.get("item_id").getAsLong())
              .setReadAt(getAsDatetime("time_read", obj))
              .setResolvedId(obj.get("resolved_id").getAsLong())
              .setResolvedTitle(obj.get("resolved_title").getAsString())
              .setResolvedUrl(obj.get("resolved_url").getAsString())
              .setStatus(newState(obj.get("status").getAsInt()).get())
              .setTitle(obj.get("given_title").getAsString())
              .setUpdatedAt(getAsDatetime("time_updated", obj))
              .setWordCount(obj.get("word_count").getAsLong());
        
        status.getImages().addAll(deserializeIterable(obj.get("images"), Image.class, context));
        status.getVideos().addAll(deserializeIterable(obj.get("videos"), Video.class, context));

        return status;
    }

    private DateTime getAsDatetime(final String item, final JsonObject obj)
    {
        final long value = obj.get(item) != null ? obj.get(item).getAsLong() : 0L;
        return value > 0 ? new DateTime() : null;
    }

    @SuppressWarnings("unchecked")
    private <T> Iterable<T> deserializeIterable(final JsonElement json, final Class<T> typeOf, final JsonDeserializationContext context)
    {
        final List<T> result = new ArrayList<T>();
        
        if (json != null)
        {
            JsonObject obj = (JsonObject) json;
            
            for (Iterator<Entry<String, JsonElement>> iter = obj.entrySet().iterator(); iter.hasNext();)
            {
                Entry<String, JsonElement> entry = iter.next();
                result.add((T) context.deserialize(entry.getValue(), typeOf));
            }
        }
        
        return result;
    }
}
