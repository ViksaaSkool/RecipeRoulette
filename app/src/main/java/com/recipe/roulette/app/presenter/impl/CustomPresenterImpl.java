package com.recipe.roulette.app.presenter.impl;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.interactor.CustomInteractor;
import com.recipe.roulette.app.model.reddit.RedditRecipeItem;
import com.recipe.roulette.app.presenter.CustomPresenter;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.view.CustomView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public final class CustomPresenterImpl extends BasePresenterImpl<CustomView> implements CustomPresenter {
    /**
     * The interactor
     */

    @NonNull
    private final CustomInteractor mInteractor;

    //private Subscriber<List<RedditRecipeItem>> mRedditRecipeItemSubscriber;
    private int resultsCount = 0;

    // The view is available using the mView variable

    @Inject
    public CustomPresenterImpl(@NonNull CustomInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }


    @Override
    public void search(String query) {
        if (getView() != null)
            getView().loading(true);
        mInteractor.searchForRecipes(query);
    }

    @Override
    public void searchForRedditRecipes(String query) {

        Subscriber<List<RedditRecipeItem>> redditRecipeItemSubscriber = new Subscriber<List<RedditRecipeItem>>() {
            @Override
            public void onCompleted() {
                LogUtil.d(Constants.API_TAG, "searchForRedditRecipes() | call completed!");
                if (getView() != null)
                    getView().onSearchResultsReady(resultsCount);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(Constants.API_TAG, "searchForRedditRecipes() | e = " + e.getMessage());
                resultsCount = Constants.RESULTS_ERROR;
                if (getView() != null)
                    getView().onSearchResultsReady(resultsCount);
            }

            @Override
            public void onNext(List<RedditRecipeItem> redditRecipeItems) {
                if (redditRecipeItems != null) {
                    LogUtil.d(Constants.API_TAG, "searchForRedditRecipes() | redditRecipeItems size is "
                            + redditRecipeItems.size());
                    resultsCount = redditRecipeItems.size();
                    RecipeRouletteApplication.getAppComponent().redditApi().setRecipeItems(redditRecipeItems);
                } else {
                    resultsCount = Constants.RESULTS_ERROR;
                    LogUtil.d(Constants.API_TAG, "searchForRedditRecipes() | redditRecipeItems IS NULL");
                }

            }
        };
        if (getView() != null)
            getView().loading(true);
        mInteractor.searchForRedditRecipes(query, redditRecipeItemSubscriber);
    }


    @Subscribe
    public void handleRecipesSearchResponse(Integer count) {
        if (getView() != null)
            getView().onSearchResultsReady(count);

    }
}