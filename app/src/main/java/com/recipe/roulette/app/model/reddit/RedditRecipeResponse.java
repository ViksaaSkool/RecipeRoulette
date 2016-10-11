
package com.recipe.roulette.app.model.reddit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.util.RedditApiUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RedditRecipeResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * @return The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return The data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Data data) {
        this.data = data;
    }


    /* list of simpler objects */
    public List<RedditRecipeItem> getRedditRecipeItems() {
        List<RedditRecipeItem> recipeItems = new ArrayList<>();
        if (data != null && data.getChildren() != null && !data.getChildren().isEmpty()) {
            for (int i = 0; i < data.getChildren().size(); i++) {
                RedditRecipeItem item = new RedditRecipeItem();

                if (data.getChildren().get(i) != null
                        && data.getChildren().get(i).getData() != null
                        && RedditApiUtil.isVideoOrGif(data.getChildren().get(i).getData())) {
                    Child child = data.getChildren().get(i);
                    if (child.getData().getThumbnail() != null)
                        item.setThumbUrl(child.getData().getThumbnail());
                    if (child.getData().getUrl() != null)
                        item.setItemLink(child.getData().getUrl());
                    if (child.getData().getTitle() != null)
                        item.setTitle(child.getData().getTitle());
                    if (child.getData().getLinkFlairText() != null)
                        item.setLinkFlairText(child.getData().getLinkFlairText());

                    item.setType(RedditApiUtil.getRecipeType(child.getData()));

                    recipeItems.add(item);
                }
            }
        }

        LogUtil.d(Constants.API_TAG, "getRedditRecipeItems() | size = " + recipeItems.size());
        return recipeItems;
    }
}
