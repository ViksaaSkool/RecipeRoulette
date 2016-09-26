package com.recipe.roulette.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerAppComponent;
import com.recipe.roulette.app.injection.module.AppModule;

public final class RecipeRouletteApplication extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    @NonNull
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}