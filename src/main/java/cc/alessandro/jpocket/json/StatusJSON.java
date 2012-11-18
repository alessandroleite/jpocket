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
		// TODO Auto-generated method stub
		return 0;
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
}