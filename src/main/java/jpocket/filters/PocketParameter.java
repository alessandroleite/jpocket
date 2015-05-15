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
package jpocket.filters;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jpocket.ContentType;
import jpocket.State;
import jpocket.Tag;
import jpocket.session.Request.Type;

import org.joda.time.DateTime;

public class PocketParameter implements Parameter, ParameterBuilder
{
    private State state;
    private boolean favorite;
    private final List<Tag> tags = new ArrayList<Tag>();
    private ContentType contentType;
    private Sort sort;
    private DetailType detailType = DetailType.COMPLETE;
    private String search;
    private String domain;
    private DateTime since;
    private Integer count;
    private Integer offSet;

    @Override
    public ParameterBuilder withState(State state)
    {
        this.state = state;
        return this;
    }

    @Override
    public ParameterBuilder wasFavorited()
    {
        this.favorite = true;

        return this;
    }

    @Override
    public ParameterBuilder withTags(Tag... tags)
    {
        this.tags.addAll(Arrays.asList(tags));
        return this;
    }

    @Override
    public ParameterBuilder sortedBy(Sort option)
    {
        this.sort = option;

        return this;
    }

    @Override
    public ParameterBuilder withDetailType(DetailType detail)
    {
        this.detailType = detail;

        return this;
    }

    @Override
    public ParameterBuilder withTitleOrUrlText(String search)
    {
        this.search = search;

        return this;
    }

    @Override
    public ParameterBuilder ofDomain(String domain)
    {
        this.domain = domain;
        return this;
    }

    @Override
    public ParameterBuilder since(DateTime date)
    {
        this.since = date;
        return this;
    }
    
    @Override
    public ParameterBuilder withContentType(ContentType type)
    {
        this.contentType = type;
        return this;
    }

    @Override
    public ParameterBuilder count()
    {
        return null;
    }

    @Override
    public List<Type> asType()
    {
        List<Type> params = new ArrayList<Type>();

        if (this.getState() != null)
        {
            params.add(new Type("state", getState().name().toLowerCase()));
        }

        if (isfavorite())
        {
            params.add(new Type("favorite", String.valueOf(isfavorite())));
        }

        if (!this.getTags().isEmpty())
        {
        }

        if (this.getContentType() != null)
        {
            params.add(new Type("contentType", getContentType().name().toLowerCase()));
        }

        if (this.getSort() != null)
        {
            params.add(new Type("sort", getSort().name().toLowerCase()));
        }

        if (this.getDetailType() != null)
        {
            params.add(new Type("detailType", getDetailType().name().toLowerCase()));
        }

        if (!isNullOrEmpty(this.getSearch()))
        {
            params.add(new Type("search", getSearch().trim()));
        }

        if (!isNullOrEmpty(this.getDomain()))
        {
            params.add(new Type("domain", getDomain()));
        }

        if (this.getSince() != null)
        {
            params.add(new Type("since", String.valueOf(getSince().getMillis())));

        }

        if (this.getCount() != null)
        {
            params.add(new Type("count", getCount().toString()));
        }

        return params;
    }

    @Override
    public State getState()
    {
        return this.state;
    }

    @Override
    public boolean isfavorite()
    {
        return this.favorite;
    }

    @Override
    public List<Tag> getTags()
    {
        return Collections.unmodifiableList(this.tags);
    }

    @Override
    public ContentType getContentType()
    {
        return this.contentType;
    }

    @Override
    public Sort getSort()
    {
        return this.sort;
    }

    @Override
    public DetailType getDetailType()
    {
        return this.detailType;
    }

    @Override
    public String getSearch()
    {
        return this.search;
    }

    @Override
    public String getDomain()
    {
        return this.domain;
    }

    @Override
    public DateTime getSince()
    {
        return this.since;
    }

    @Override
    public Integer getCount()
    {
        return this.count;
    }

    @Override
    public Integer getOffSet()
    {
        return offSet;
    }
}
