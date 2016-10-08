package com.recipe.roulette.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerAppComponent;
import com.recipe.roulette.app.injection.module.AppModule;
import io.fabric.sdk.android.Fabric;

public final class RecipeRouletteApplication extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    @NonNull
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}