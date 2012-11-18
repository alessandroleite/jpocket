package cc.alessandro.jpocket;

public interface Status extends Comparable<Status>, PocketResponse {

	/**
	 * @return Return A unique identifier matching the saved item. This id must
	 *         be used to perform any actions through the <a
	 *         href="http://getpocket.com/developer/docs/v3/modify">v3/modify
	 *         endpoint</a>
	 */
	long getId();

	/**
	 * @return A unique identifier similar to the item_id but is unique to the
	 *         actual url of the saved item. The resolved_id identifies unique
	 *         urls. For example a direct link to a New York Times article and a
	 *         link that redirects (ex a shortened bit.ly url) to the same
	 *         article will share the same resolved_id. If this value is 0, it
	 *         means that Pocket has not processed the item. Normally this
	 *         happens within seconds but is possible you may request the item
	 *         before it has been resolved.
	 */
	long getResolvedId();

	/**
	 * @return The actual url that was saved with the item. This url should be
	 *         used if the user wants to view the item.
	 */
	String getGivenUrl();

	/**
	 * @return The final url of the item. For example if the item was a
	 *         shortened bit.ly link, this will be the actual article the url
	 *         linked to.
	 */
	String getResolvedUrl();

	/**
	 * @return The title that was saved along with the item.
	 */
	String getTitle();

	/**
	 * @return The title that Pocket found for the item when it was parsed
	 */
	String getResolvedTitle();

	/**
	 * 
	 * @return <code>true</code> if the item was favorited.
	 */
	boolean isFavorite();

	/**
	 * @return Return the state of this item.
	 */
	State getStatus();

	/**
	 * @return The first few lines of the item (articles only).
	 */
	String getExcerpt();
	
	/**
	 * @return Return <code>true</code> if the item is an article.
	 */
	boolean isArticle();
	
	/**
	 * @return the images associated with the item.
	 */
	Images getImages();
	
	/**
	 * @return the videos associated with the item.
	 */
	Videos getVideos();
	
	/**
	 * @return Return the number of words in the article.
	 */
	long getWordCount();
}
