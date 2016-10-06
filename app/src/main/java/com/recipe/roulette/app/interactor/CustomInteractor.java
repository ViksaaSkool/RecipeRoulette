package com.recipe.roulette.app.interactor;

import android.support.v7.app.AppCompatActivity;

public interface CustomInteractor extends BaseInteractor {

    void searchForRecipes(String query);

    void setRecipeView(AppCompatActivity a);
}