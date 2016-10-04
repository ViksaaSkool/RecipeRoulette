package com.recipe.roulette.app.presenter.impl;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.RecipeInteractor;
import com.recipe.roulette.app.presenter.RecipePresenter;
import com.recipe.roulette.app.view.RecipeView;

import javax.inject.Inject;

public final class RecipePresenterImpl extends BasePresenterImpl<RecipeView> implements RecipePresenter {
    /**
     * The interactor
     */


    @NonNull
    private final RecipeInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public RecipePresenterImpl(@NonNull RecipeInteractor interactor) {
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