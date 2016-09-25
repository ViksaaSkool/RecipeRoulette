package com.recipe.roulette.app.injection.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.injection.module.AppModule;
import com.recipe.roulette.app.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    Context getAppContext();

    RecipeRouletteApplication getApp();

    Retrofit retrofit();

    OkHttpClient okHttpClient();

    SharedPreferences sharedPreferences();
}