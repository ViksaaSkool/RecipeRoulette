package com.recipe.roulette.app.injection.component;

import android.content.Context;

import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.injection.module.AppModule;
import com.recipe.roulette.app.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Context getAppContext();

    RecipeRouletteApplication getApp();


    //void inject(MainActivity activity);
}