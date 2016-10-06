package com.recipe.roulette.app.interactor.impl;

import android.support.v7.app.AppCompatActivity;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.helpers.ChangeFragmentHelper;
import com.recipe.roulette.app.interactor.CustomInteractor;

import javax.inject.Inject;

import static com.recipe.roulette.app.RecipeRouletteApplication.getAppComponent;

public final class CustomInteractorImpl extends BaseInteractiorImpl implements CustomInteractor {


    @Inject
    public CustomInteractorImpl() {

    }

    @Override
    public void searchForRecipes(String query) {
        getAppComponent().food2ForkApi().searchForRecipes(query);
    }

    @Override
    public void setRecipeView(AppCompatActivity a) {
        boolean view = RecipeRouletteApplication.getAppComponent().sharedPreferences().getBoolean(Constants.LAYOUT_KEY, Constants.LIST_VIEW);
        if (view)
            ChangeFragmentHelper.setRecipeListFragment(a, R.id.main_fragment);
        else
            ChangeFragmentHelper.setRecipeSwipeFragment(a, R.id.main_fragment);
    }


}