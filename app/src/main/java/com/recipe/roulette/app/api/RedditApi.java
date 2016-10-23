package com.recipe.roulette.app.api;

import com.recipe.roulette.app.model.reddit.RedditRecipeItem;

import java.util.List;

/**
 * Created by varsovski on 10-Oct-16.
 */

public class RedditApi {

    private List<RedditRecipeItem> mRecipeItems;

    public List<RedditRecipeItem> getRecipeItems() {
        return mRecipeItems;
    }

    public void setRecipeItems(List<RedditRecipeItem> mRecipeItems) {
        this.mRecipeItems = mRecipeItems;
    }
}
