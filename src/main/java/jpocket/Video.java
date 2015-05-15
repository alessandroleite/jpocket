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

import static com.google.common.base.Objects.*;

public class Video implements Item
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = -1979054316447325051L;

    private Long id;

    private String src;

    private Dimension dimension = new Dimension();

    private Integer type;

    private String vid;

    private Item parent;

    /**
     * @return the id
     */
    @Override
    public Long getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public Video setId(Long id)
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
     * @param src
     *            the src to set
     */
    public Video setSrc(String src)
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

    public Video setDimension(int width, int height)
    {
        this.dimension = new Dimension(width, height);
        return this;
    }

    public Video setDimension(double width, double height)
    {
        this.dimension = new Dimension();
        dimension.setSize(width, height);
        return this;
    }

    /**
     * @return the type
     */
    public Integer getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public Video setType(Integer type)
    {
        this.type = type;
        return this;
    }

    /**
     * @return the vid
     */
    public String getVid()
    {
        return vid;
    }

    /**
     * @param vid
     *            the vid to set
     */
    public Video setVid(String vid)
    {
        this.vid = vid;
        return this;
    }

    @Override
    public Item getParent()
    {
        return this.parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(Item parent)
    {
        this.parent = parent;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this.getClass()).add("id", getId()).add("src", getSrc()).add("width", getDimension().getWidth())
                .add("height", getDimension().getHeight()).add("type", getType()).add("vid", getVid()).omitNullValues().toString();
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

        Video other = (Video) obj;
        return equal(getId(), other.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId()) * 17;
    }

}
