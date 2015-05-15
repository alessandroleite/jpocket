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

import jpocket.Image;
import jpocket.Article;
import jpocket.Items;
import jpocket.Video;
import jpocket.auth.AccessToken;
import jpocket.auth.RequestToken;
import jpocket.config.Application;
import jpocket.config.Credential;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonFactory
{
    private GsonFactory()
    {
        throw new UnsupportedOperationException();
    }

    public static <T> T parser(String json, Class<T> type)
    {
        Gson gson = getGsonBuilder();

        return gson.fromJson(json, type);
    }

    public static Gson getGsonBuilder()
    {
        Gson gson = new GsonBuilder()
        
                .registerTypeAdapter(AccessToken.class, new AccessTokenDeserializer())
                .registerTypeAdapter(AccessToken.class, new AccessTokenSerialize())
                
                .registerTypeAdapter(Application.class, new ApplicationDeserializer())
                .registerTypeAdapter(Application.class, new ApplicationSerializer())
                
                .registerTypeAdapter(Credential.class, new CredentialDeserializer())
                .registerTypeAdapter(Credential.class, new CredentialSerializer())
                
                .registerTypeAdapter(Article.class, new ItemDeserializer())
                .registerTypeAdapter(Items.class, new ItemsDeserializer())
                
                .registerTypeAdapter(Image.class, new ImageDeserializer())
                .registerTypeAdapter(RequestToken.class, new RequestTokenSerializer())
                
                .registerTypeAdapter(Video.class, new VideoDeserializer())
                .create();
        return gson;
    }
}
