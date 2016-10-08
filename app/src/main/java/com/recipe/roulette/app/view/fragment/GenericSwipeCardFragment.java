package com.recipe.roulette.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.Recipe;

/**
 * Created by varsovski on 08-Oct-16.
 */

public class GenericSwipeCardFragment extends Fragment {

    private Recipe mRecipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRecipe();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUI();
    }

    private void setRecipe() {
        int index = getArguments().getInt(Constants.SWIPE_CARD_INDEX_KEY);
        mRecipe = RecipeRouletteApplication
                .getAppComponent()
                .food2ForkApi()
                .getSearchResults()
                .getRecipes()
                .get(index);
    }

    public void setUI() {

    }

}
