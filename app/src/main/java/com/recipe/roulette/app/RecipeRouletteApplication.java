package com.recipe.roulette.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.module.AppModule;
import com.recipe.roulette.app.injection.DaggerAppComponent;

public final class RecipeRouletteApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();



        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}