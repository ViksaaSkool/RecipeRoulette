package com.recipe.roulette.app.injection;

import android.content.Context;

import com.recipe.roulette.app.RecipeRouletteApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    RecipeRouletteApplication getApp();
}