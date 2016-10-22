package com.recipe.roulette.app.interactor.impl;

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




}