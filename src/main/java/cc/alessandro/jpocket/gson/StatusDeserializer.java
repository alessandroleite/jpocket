/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
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
package cc.alessandro.jpocket.gson;

import java.lang.reflect.Type;

import cc.alessandro.jpocket.State;
import cc.alessandro.jpocket.Status;
import cc.alessandro.jpocket.json.StatusJSON;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class StatusDeserializer implements JsonDeserializer<Status>{

	@Override
	public Status deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		JsonObject jsonObject = (JsonObject) json;
		
		StatusJSON status = new StatusJSON();
		status.setId(jsonObject.get("item_id").getAsLong());
		status.setResolvedId(jsonObject.get("resolved_id").getAsLong());
		status.setGivenUrl(jsonObject.get("given_url").getAsString());
		status.setTitle(jsonObject.get("given_title").getAsString());
		status.setFavorite(jsonObject.get("favorite").getAsInt() == 1);
		status.setStatus(State.valueOf(jsonObject.get("status").getAsInt()));
		status.setExcerpt(jsonObject.get("excerpt").getAsString());
		status.setArticle(jsonObject.get("is_article").getAsInt() == 1);
		status.setWordCount(jsonObject.get("word_count").getAsLong());
		
		//TODO images, videos and tags
		
		return status;
	}
}
