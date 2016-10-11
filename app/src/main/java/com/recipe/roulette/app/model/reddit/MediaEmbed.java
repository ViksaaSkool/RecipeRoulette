
package com.recipe.roulette.app.model.reddit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MediaEmbed {

    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("scrolling")
    @Expose
    private Boolean scrolling;
    @SerializedName("height")
    @Expose
    private Integer height;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The scrolling
     */
    public Boolean getScrolling() {
        return scrolling;
    }

    /**
     * 
     * @param scrolling
     *     The scrolling
     */
    public void setScrolling(Boolean scrolling) {
        this.scrolling = scrolling;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

}
