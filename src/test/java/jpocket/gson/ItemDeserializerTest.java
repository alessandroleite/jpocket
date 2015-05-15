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

import static com.google.common.base.Joiner.on;
import static org.apache.commons.io.IOUtils.readLines;

import java.io.IOException;

import jpocket.Article;
import jpocket.Items;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.gson.JsonSyntaxException;

import static org.assertj.core.api.Assertions.*;

public class ItemDeserializerTest
{
    @Test
    public void testItemDeserialize() throws JsonSyntaxException, IOException
    {
        String json = on("\n").join(readLines(this.getClass().getClassLoader().getResourceAsStream("article.example")));
        Items items = GsonFactory.getGsonBuilder().fromJson(json, Items.class);
        
        assertThat(items).isNotNull();
        assertThat(items.size()).isEqualTo(1);
        
        Optional<Article> first = items.first();
        assertThat(first.isPresent()).isTrue();
        
        assertThat(first.get().getId()).isEqualTo(229279689L);
        assertThat(first.get().getTitle()).isEqualTo("The Massive Ryder Cup Preview - The Triangle Blog - Grantland");
        assertThat(first.get().getResolvedTitle()).isEqualTo("The Massive Ryder Cup Preview");
        assertThat(first.get().getExcerpt()).isEqualTo("The list of things I love about the Ryder Cup is so long that it could fill a (tedious) novel, and golf fans can probably guess most of them.");
        
       assertThat(first.get().getImages().size()).isEqualTo(1);
       assertThat(first.get().getVideos().size()).isEqualTo(1);
    }
}
