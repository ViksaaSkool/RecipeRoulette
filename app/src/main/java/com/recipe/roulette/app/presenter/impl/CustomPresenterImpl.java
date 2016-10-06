package com.recipe.roulette.app.presenter.impl;

import android.support.annotation.NonNull;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.interactor.CustomInteractor;
import com.recipe.roulette.app.model.RecipesSearchResponse;
import com.recipe.roulette.app.presenter.CustomPresenter;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.view.CustomView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public final class CustomPresenterImpl extends BasePresenterImpl<CustomView> implements CustomPresenter {
    /**
     * The interactor
     */

    @NonNull
    private final CustomInteractor mInteractor;

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

        mInteractor.searchForRecipes(query);

    }

    @Override
    public void setRecipeView(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
            buttonView.setText(R.string.title_list);
        else
            buttonView.setText(R.string.title_swipe);

        RecipeRouletteApplication.getAppComponent().sharedPreferences().edit().putBoolean(Constants.LAYOUT_KEY, isChecked).apply();
    }

    @Subscribe
    public void handleRecipesSearchResponse(RecipesSearchResponse recipesSearchResponse) {
        if (recipesSearchResponse != null
                && recipesSearchResponse.getCount() != null
                && !recipesSearchResponse.getCount().equals(0)) {
            LogUtil.d(Constants.API_TAG, "CustomPresenterImpl | handleRecipesSearchResponse(), count == " + recipesSearchResponse.getCount());

        } else {

            Toast.makeText(RecipeRouletteApplication.getAppComponent().getApp(), R.string.notification_no_results, Toast.LENGTH_SHORT).show();
        }

    }
}