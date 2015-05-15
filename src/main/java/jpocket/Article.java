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
package jpocket;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import com.google.common.base.Objects;

public class Article implements Comparable<Article>, Item
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = -2563529421083479153L;

    private Long id;
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
    
    private DateTime addedAt;
    private DateTime updatedAt;
    private DateTime readAt;
    
    private Item parent;
    
    private final Videos videos = new Videos(this);
    private final Images images = new Images(this);

    /**
     * <p>
     * Returns a unique identifier matching the saved item. This id must be used to perform any actions through the 
     * <a href="http://getpocket.com/developer/docs/v3/modify">v3/modify endpoint</a>
     * 
     * @return a unique identifier matching a saved item.
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * Returns a unique identifier similar to the {{@link #getId()} but is unique to the actual URL of the saved item. 
     * <p> The resolved_id identifies unique URLs. For example a direct link to a New York Times article and a link that redirects 
     * (i.e., a shortened bit.ly URL) to the same article will share the same resolved_id. 
     * 
     * <p>If this value is 0, it means that Pocket has not processed the item. Normally this happens within seconds but is possible 
     * you may request the item before it has been resolved.
     * 
     * @return a unique identifier of this item
     */
    public long getResolvedId()
    {
        return this.resolvedId;
    }

    /**
     * Returns the actual URL that was saved with the item. This URL should be used if the user wants to view the item.
     * @return the actual URL that was saved with the item. This URL should be used if the user wants to view the item.
     */
    public String getGivenUrl()
    {
        return this.givenUrl;
    }

    /**
     * Returns the resolved URL of this item.
     * @return final URL of this item. For example if the item was a shortened bit.ly link, this will be the actual article the URL linked to.
     */
    public String getResolvedUrl()
    {
        return this.resolvedUrl;
    }

    /**
     * Returns the title that was saved along with this item.
     * 
     * @return the title that was saved along with this item
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Returns the title of this item.
     * 
     * @return the title that Pocket found when the item when was parsed
     */
    public String getResolvedTitle()
    {
        return this.resolvedTitle;
    }

    /**
     * Returns <code>true</code> if this item was favorite; <code>false</code> otherwise.
     * @return <code>true</code> if the item was favorite.
     */
    public boolean isFavorite()
    {
        return this.favorite;
    }

    /**
     * Returns the status of this item.
     * 
     * @return the state of this item. It is never <code>null</code>.
     */
    public State getStatus()
    {
        return this.status;
    }

    /**
     * Returns the first few lines of text.
     * @return the first few lines of the item.
     */
    public String getExcerpt()
    {
        return this.excerpt;
    }

    /**
     * Returns <code>true</code> if this item represents an article, <code>false</code> otherwise.
     * @return <code>true</code> if the item is an article.
     */
    public boolean isArticle()
    {
        return article;
    }

    /**
     * Returns the images associated with this item.
     * @return the images associated with this item. It is never <code>null</code>
     */
    public Images getImages()
    {
        return this.images;
    }

    
    /**
     * Returns the videos associated with the item.
     * @return the videos associated with the item
     */
    public Videos getVideos()
    {
        return this.videos;
    }

    /**
     * Returns the number of words in the text.
     * @return the number of words in this item's text. 
     */
    public long getWordCount()
    {
        return this.wordCount;
    }

    /**
     * @param id the id to set
     */
    public Article setId(Long id)
    {
        this.id = id;
        return this;
    }

    /**
     * @param resolvedId the resolvedId to set
     */
    public Article setResolvedId(long resolvedId)
    {
        this.resolvedId = resolvedId;
        return this;
    }

    /**
     * @param givenUrl the givenUrl to set
     */
    public Article setGivenUrl(String givenUrl)
    {
        this.givenUrl = givenUrl;
        return this;
    }

    /**
     * @param resolvedUrl the resolvedUrl to set
     * @return the same instance with the new {@code resolvedUrl}
     */
    public Article setResolvedUrl(String resolvedUrl)
    {
        this.resolvedUrl = resolvedUrl;
        return this;
    }

    /**
     * @param title the title to set
     * @return the same instance with the title assigned
     */
    public Article setTitle(String title)
    {
        this.title = title;
        return this;
    }

    /**
     * @param resolvedTitle the resolvedTitle to set
     * @return the same instance with the resolved title
     */
    public Article setResolvedTitle(String resolvedTitle)
    {
        this.resolvedTitle = resolvedTitle;
        return this;
    }

    /**
     * @param favorite the favorite to set
     */
    public Article setFavorite(boolean favorite)
    {
        this.favorite = favorite;
        return this;
    }

    /**
     * @param status the status to set
     */
    public Article setStatus(State status)
    {
        this.status = status;
        return this;
    }

    /**
     * @param excerpt the excerpt to set
     */
    public Article setExcerpt(String excerpt)
    {
        this.excerpt = excerpt;
        return this;
    }

    /**
     * @param article the article to set
     */
    public Article setArticle(boolean article)
    {
        this.article = article;
        return this;
    }

    /**
     * @param wordCount the wordCount to set
     */
    public Article setWordCount(long wordCount)
    {
        this.wordCount = wordCount;
        return this;
    }
    
    @Override
    public Item getParent()
    {
        return this.parent;
    }
    
    /**
     * @param parent the parent to set
     */
    public Article setParent(Item parent)
    {
        this.parent = parent;
        return this;
    }
    
    

    /**
     * @return the addAt
     */
    public DateTime getAddedAt()
    {
        return addedAt;
    }

    /**
     * @param addAt the addedAt to set
     */
    public Article setAddedAt(DateTime addAt)
    {
        this.addedAt = addAt;
        return this;
    }
    
    

    /**
     * @return the updatedAt
     */
    public DateTime getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public Article setUpdatedAt(DateTime updatedAt)
    {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * @return the readAt
     */
    public DateTime getReadAt()
    {
        return readAt;
    }

    /**
     * @param readAt the readAt to set
     */
    public Article setReadAt(DateTime readAt)
    {
        this.readAt = readAt;
        return this;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass())
        {
            return false;
        }

        final Article other = (Article) obj;
        return Objects.equal(getId(), other.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId()) * 31;
    }
    
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    @Override
    public int compareTo(Article other)
    {
        return getId().compareTo(other.getId());
    }
}
