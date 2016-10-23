package com.recipe.roulette.app.interactor.impl;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.interactor.CustomInteractor;
import com.recipe.roulette.app.model.reddit.RedditRecipeItem;
import com.recipe.roulette.app.model.reddit.RedditRecipeResponse;
import com.recipe.roulette.app.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

public final class CustomInteractorImpl extends BaseInteractiorImpl implements CustomInteractor {


    @Inject
    public CustomInteractorImpl() {
        super();
    }

    @Override
    public void searchForRecipes(String query) {
        mFood2ForkApi.searchForRecipes(query);
    }

    @Override
    public void searchForRedditRecipes(String query, Subscriber<List<RedditRecipeItem>> redditRecipeResponseSubscriber) {
        String searchQuery = Constants.QUERY_SEARCH_VALUE.concat(query);

        Observable<RedditRecipeResponse> gifRecipesObservable = redditModuleApiInterface.searchSubreddit(Constants.SUBREDDIT_GIF_RECIPES,
                searchQuery,
                Constants.RESTRICT_VALUE,
                Constants.SORT_VALUE,
                Constants.SCOPE_VALUE);

        Observable<RedditRecipeResponse> videoRecipesObservable = redditModuleApiInterface.searchSubreddit(Constants.SUBREDDIT_COOKING_VIDEOS,
                searchQuery,
                Constants.RESTRICT_VALUE,
                Constants.SORT_VALUE,
                Constants.SCOPE_VALUE);


        Observable<RedditRecipeResponse> recipesGifObservable = redditModuleApiInterface.searchSubreddit(Constants.SUBREDDIT_RECIPE_GIFS,
                searchQuery,
                Constants.RESTRICT_VALUE,
                Constants.SORT_VALUE,
                Constants.SCOPE_VALUE);


        Observable.combineLatest(gifRecipesObservable, videoRecipesObservable, recipesGifObservable,
                new Func3<RedditRecipeResponse, RedditRecipeResponse, RedditRecipeResponse, List<RedditRecipeItem>>() {

                    @Override
                    public List<RedditRecipeItem> call(RedditRecipeResponse redditRecipeResponse1,
                                                       RedditRecipeResponse redditRecipeResponse2,
                                                       RedditRecipeResponse redditRecipeResponse3) {

                        List<RedditRecipeItem> recipeItems = new ArrayList<>();
                        recipeItems.addAll(redditRecipeResponse1.getRedditRecipeItems());
                        recipeItems.addAll(redditRecipeResponse2.getRedditRecipeItems());
                        recipeItems.addAll(redditRecipeResponse3.getRedditRecipeItems());

                        LogUtil.d(Constants.API_TAG, "combineLatest() call | I've just combined!"
                                + "\n gifRecipes size = " + redditRecipeResponse1.getRedditRecipeItems().size()
                                + "\n videoRecipes size = " + redditRecipeResponse2.getRedditRecipeItems().size()
                                + "\n recipesGif size = " + redditRecipeResponse3.getRedditRecipeItems().size());

                        return recipeItems;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(redditRecipeResponseSubscriber);
    }


}