package com.recipe.roulette.app.presenter.impl;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.RecipeListInteractor;
import com.recipe.roulette.app.presenter.RecipeListPresenter;
import com.recipe.roulette.app.view.RecipeListView;

import javax.inject.Inject;

public final class RecipeListPresenterImpl extends BasePresenterImpl<RecipeListView> implements RecipeListPresenter {
    /**
     * The interactor
     */


    @NonNull
    private final RecipeListInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public RecipeListPresenterImpl(@NonNull RecipeListInteractor interactor) {
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