package com.recipe.roulette.app.interactor.impl;

import com.recipe.roulette.app.api.Food2ForkApi;
import com.recipe.roulette.app.injection.module.RedditModule;
import com.recipe.roulette.app.interactor.BaseInteractor;

import javax.inject.Inject;

import static com.recipe.roulette.app.RecipeRouletteApplication.getAppComponent;

/**
 * Created by varsovski on 25-Sep-16.
 */

public class BaseInteractiorImpl implements BaseInteractor {

    @Inject
    Food2ForkApi mFood2ForkApi;

    @Inject
    RedditModule.RedditModuleApiInterface redditModuleApiInterface;

    @Inject
    BaseInteractiorImpl(){
        getAppComponent().inject(this);
    }
}
