package com.recipe.roulette.app.presenter.impl;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.DetailsInteractor;
import com.recipe.roulette.app.presenter.DetailsPresenter;
import com.recipe.roulette.app.view.DetailsView;

import javax.inject.Inject;

public final class DetailsPresenterImpl extends BasePresenterImpl<DetailsView> implements DetailsPresenter {
    /**
     * The interactor
     */

    @NonNull
    private final DetailsInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public DetailsPresenterImpl(@NonNull DetailsInteractor interactor) {
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