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
import java.util.Iterator;
import java.util.Map.Entry;

import jpocket.Article;
import jpocket.State;
import jpocket.Items;

import com.google.common.base.Optional;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import static java.lang.String.*;
import static jpocket.State.*;
import static com.google.common.base.Preconditions.*;

public class ItemsDeserializer implements JsonDeserializer<Items>
{
    @Override
    public Items deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        Items statuses = null;

        if (json instanceof JsonObject && ((JsonObject) json).get("status") != null)
        {
            JsonObject obj = (JsonObject) json;

            Optional<State> state = newState(obj.get("status").getAsInt());
            checkState(state.isPresent(), format("Invalid status code %s", obj.get("status").getAsInt()));
            
            statuses = new Items(state.get());
            deserialize(context, statuses, obj);
        }

        return statuses;
    }

    private void deserialize(JsonDeserializationContext context, Items items, JsonObject obj)
    {
        JsonObject list = (JsonObject) obj.get("list");
        
        for (Iterator<Entry<String, JsonElement>> iter = list.entrySet().iterator(); iter.hasNext();)
        {
            Entry<String, JsonElement> entry = iter.next();
            items.add((Article) context.deserialize(entry.getValue(), Article.class));
        }
    }
}
