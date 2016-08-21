
package com.recipe.roulette.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Recipe {

    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("f2f_url")
    @Expose
    private String f2fUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("recipe_id")
    @Expose
    private String recipeId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("social_rank")
    @Expose
    private Integer socialRank;
    @SerializedName("publisher_url")
    @Expose
    private String publisherUrl;

    /**
     * 
     * @return
     *     The publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The f2fUrl
     */
    public String getF2fUrl() {
        return f2fUrl;
    }

    /**
     * 
     * @param f2fUrl
     *     The f2f_url
     */
    public void setF2fUrl(String f2fUrl) {
        this.f2fUrl = f2fUrl;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * 
     * @param sourceUrl
     *     The source_url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     * 
     * @return
     *     The recipeId
     */
    public String getRecipeId() {
        return recipeId;
    }

    /**
     * 
     * @param recipeId
     *     The recipe_id
     */
    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 
     * @return
     *     The socialRank
     */
    public Integer getSocialRank() {
        return socialRank;
    }

    /**
     * 
     * @param socialRank
     *     The social_rank
     */
    public void setSocialRank(Integer socialRank) {
        this.socialRank = socialRank;
    }

    /**
     * 
     * @return
     *     The publisherUrl
     */
    public String getPublisherUrl() {
        return publisherUrl;
    }

    /**
     * 
     * @param publisherUrl
     *     The publisher_url
     */
    public void setPublisherUrl(String publisherUrl) {
        this.publisherUrl = publisherUrl;
    }

}
