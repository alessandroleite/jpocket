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

import java.awt.Dimension;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Image implements Item
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = 6686889443460153215L;

    /**
     * The image id
     */
    private Long id;

    /**
     * The image source
     */
    private String src;

    /**
     * The image dimension
     */
    private Dimension dimension = new Dimension();

    /**
     * The owner of the image.
     */
    private String credit;

    /**
     * The image caption.
     */
    private String caption;
    
    /**
     * The item (i.e., article) which this image belongs to.
     */
    private Item parent;

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public Image setId(Long id)
    {
        this.id = id;
        return this;
    }

    /**
     * @return the src
     */
    public String getSrc()
    {
        return src;
    }

    /**
     * @param src the src to set
     */
    public Image setSrc(String src)
    {
        this.src = src;
        return this;
    }

    /**
     * @return the dimension
     */
    public Dimension getDimension()
    {
        return dimension;
    }
    
    public Image setDimension(double width, double height)
    {
        this.dimension = new Dimension();
        dimension.setSize(width, height);
        return this;
    }
    
    public Image setDimension(int width, int height)
    {
        this.dimension = new Dimension(width, height);
        return this;
    }

    /**
     * @return the credit
     */
    public String getCredit()
    {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public Image setCredit(String credit)
    {
        this.credit = credit;
        return this;
    }

    /**
     * @return the caption
     */
    public String getCaption()
    {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public Image setCaption(String caption)
    {
        this.caption = caption;
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
    public void setParent(Item parent)
    {
        this.parent = parent;
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
        
        Image other = (Image) obj;
        
        return Objects.equal(getId(), other.getId()) ||
               Objects.equal(getSrc(), other.getSrc());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId()) * 17;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this.getClass())
                .add("id", getId())
                .add("source", getSrc())
                .add("width", getDimension().getWidth())
                .add("height", getDimension().getHeight())
                .add("credit", getCredit())
                .add("caption", getCaption())
                .omitNullValues()
                .toString();
    }

}
