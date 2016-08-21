package com.recipe.roulette.app.injection.component;

import android.content.Context;

import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    RecipeRouletteApplication getApp();
}