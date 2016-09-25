package com.recipe.roulette.app.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.recipe.roulette.app.RecipeRouletteApplication;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {


    @NonNull
    private final RecipeRouletteApplication mApp;

    public AppModule(@NonNull RecipeRouletteApplication app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    public RecipeRouletteApplication provideApp() {
        return mApp;
    }

}
