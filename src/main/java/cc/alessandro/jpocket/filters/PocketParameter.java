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
package cc.alessandro.jpocket.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import static com.google.common.base.Strings.*;

import cc.alessandro.jpocket.ContentType;
import cc.alessandro.jpocket.State;
import cc.alessandro.jpocket.Tag;
import cc.alessandro.jpocket.session.Request;
import cc.alessandro.jpocket.session.Request.Type;

public class PocketParameter implements Parameter, ParameterBuilder {

	private State state;
	private boolean favorite;
	private List<Tag> tags = new ArrayList<Tag>();
	private ContentType contentType;
	private Sort sort;
	private DetailType detailType;
	private String search;
	private String domain;
	private DateTime since;
	private Integer count;
	private Integer offSet;

	@Override
	public ParameterBuilder withState(State state) {
		this.state = state;
		return this;
	}

	@Override
	public ParameterBuilder wasFavorited() {
		this.favorite = true;

		return this;
	}

	@Override
	public ParameterBuilder withTags(Tag... tags) {
		this.tags.addAll(Arrays.asList(tags));
		return this;
	}

	@Override
	public ParameterBuilder sortedBy(Sort option) {
		this.sort = option;

		return this;
	}

	@Override
	public ParameterBuilder withDetailType(DetailType detail) {
		this.detailType = detail;

		return this;
	}

	@Override
	public ParameterBuilder withTitleOrUrlText(String search) {
		this.search = search;

		return this;
	}

	@Override
	public ParameterBuilder ofDomain(String domain) {
		this.domain = domain;
		return this;
	}

	@Override
	public ParameterBuilder since(DateTime date) {
		this.since = date;

		return this;
	}

	@Override
	public ParameterBuilder count() {
		return null;
	}

	@Override
	public List<Type> asType() {
		List<Type> params = new ArrayList<Request.Type>();

		if (this.getState() != null) {
			params.add(new Type("state", this.getState().name().toLowerCase()));
		}

		if (isfavorite()) {
			params.add(new Type("favorite", String.valueOf(this.isfavorite())));
		}
		
		if (!this.getTags().isEmpty()){
		}
		
		if (this.getContentType() != null){
			params.add(new Type("contentType", this.getContentType().name().toLowerCase()));
		}
		
		if (this.getSort() != null){
			params.add(new Type("sort", this.getSort().name().toLowerCase()));
		}
		
		if (this.getDetailType() != null){
			params.add(new Type("detailType", this.getDetailType().name().toLowerCase()));
		}
		
		if (!isNullOrEmpty(this.getSearch())){
			params.add(new Type("search", this.getSearch().trim()));
		}
		
		if (!isNullOrEmpty(this.getDomain())){
			params.add(new Type("domain", this.getDomain()));
		}
		
		if (this.getSince() != null){
			params.add(new Type("since", String.valueOf(this.getSince().getMillis())));
			
		}
		
		if (this.getCount() != null){
			params.add(new Type("count", this.getCount().toString()));
		}

		return params;
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public boolean isfavorite() {
		return this.favorite;
	}

	@Override
	public List<Tag> getTags() {
		return Collections.unmodifiableList(this.tags);
	}

	@Override
	public ContentType getContentType() {
		return this.contentType;
	}

	@Override
	public Sort getSort() {
		return this.sort;
	}

	@Override
	public DetailType getDetailType() {
		return this.detailType;
	}

	@Override
	public String getSearch() {
		return this.search;
	}

	@Override
	public String getDomain() {
		return this.domain;
	}

	@Override
	public DateTime getSince() {
		return this.since;
	}

	@Override
	public Integer getCount() {
		return this.count;
	}

	@Override
	public Integer getOffSet() {
		return offSet;
	}
}