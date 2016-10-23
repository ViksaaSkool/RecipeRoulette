package com.recipe.roulette.app.interactor;

import com.recipe.roulette.app.model.reddit.RedditRecipeItem;

import java.util.List;

import rx.Subscriber;

public interface CustomInteractor extends BaseInteractor {

    void searchForRecipes(String query);

    void searchForRedditRecipes(String query, Subscriber<List<RedditRecipeItem>> redditRecipeResponseSubscriber);

}