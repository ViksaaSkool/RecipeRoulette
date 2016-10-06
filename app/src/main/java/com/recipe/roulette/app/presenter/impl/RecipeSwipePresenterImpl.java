package com.recipe.roulette.app.presenter.impl;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.RecipeSwipeInteractor;
import com.recipe.roulette.app.presenter.RecipeSwipePresenter;
import com.recipe.roulette.app.view.RecipeSwipeView;

import javax.inject.Inject;

public final class RecipeSwipePresenterImpl extends BasePresenterImpl<RecipeSwipeView> implements RecipeSwipePresenter {
    /**
     * The interactor
     */


    @NonNull
    private final RecipeSwipeInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public RecipeSwipePresenterImpl(@NonNull RecipeSwipeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);


        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

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
}