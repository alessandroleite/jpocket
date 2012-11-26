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
package cc.alessandro.jpocket.json;

import cc.alessandro.jpocket.Images;
import cc.alessandro.jpocket.State;
import cc.alessandro.jpocket.Status;
import cc.alessandro.jpocket.Videos;

public class StatusJSON implements Status {

	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = -4439055812190031609L;
	
	private long id;
	private long resolvedId;
	private String givenUrl;
	private String resolvedUrl;
	private String title;
	private String resolvedTitle;
	private boolean favorite;
	private State status;
	private String excerpt;
	private boolean article;
	private long wordCount;
	private Videos videos;
	private Images images;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (!(obj instanceof StatusJSON))
			return false;
		
		StatusJSON other = (StatusJSON) obj;
		
		return this.getId() == other.getId();
	}
	
	@Override
	public int hashCode() {
		return (int) this.getId() * 37;
	}

	@Override
	public int compareTo(Status otherStatus) {
		return Long.valueOf(status.getId()).compareTo(Long.valueOf(otherStatus.getId()));
	}
	

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public long getResolvedId() {
		return this.resolvedId;
	}

	@Override
	public String getGivenUrl() {
		return this.givenUrl;
	}

	@Override
	public String getResolvedUrl() {
		return this.resolvedUrl;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getResolvedTitle() {
		return this.resolvedTitle;
	}

	@Override
	public boolean isFavorite() {
		return this.favorite;
	}

	@Override
	public State getStatus() {
		return this.status;
	}

	@Override
	public String getExcerpt() {
		return this.excerpt;
	}

	@Override
	public boolean isArticle() {
		return article;
	}

	@Override
	public Images getImages() {
		return this.images;
	}

	@Override
	public Videos getVideos() {
		return this.videos;
	}

	@Override
	public long getWordCount() {
		return this.wordCount;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param resolvedId the resolvedId to set
	 */
	public void setResolvedId(long resolvedId) {
		this.resolvedId = resolvedId;
	}

	/**
	 * @param givenUrl the givenUrl to set
	 */
	public void setGivenUrl(String givenUrl) {
		this.givenUrl = givenUrl;
	}

	/**
	 * @param resolvedUrl the resolvedUrl to set
	 */
	public void setResolvedUrl(String resolvedUrl) {
		this.resolvedUrl = resolvedUrl;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param resolvedTitle the resolvedTitle to set
	 */
	public void setResolvedTitle(String resolvedTitle) {
		this.resolvedTitle = resolvedTitle;
	}

	/**
	 * @param favorite the favorite to set
	 */
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(State status) {
		this.status = status;
	}

	/**
	 * @param excerpt the excerpt to set
	 */
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(boolean article) {
		this.article = article;
	}

	/**
	 * @param wordCount the wordCount to set
	 */
	public void setWordCount(long wordCount) {
		this.wordCount = wordCount;
	}

	/**
	 * @param videos the videos to set
	 */
	public void setVideos(Videos videos) {
		this.videos = videos;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(Images images) {
		this.images = images;
	}
	
	
}