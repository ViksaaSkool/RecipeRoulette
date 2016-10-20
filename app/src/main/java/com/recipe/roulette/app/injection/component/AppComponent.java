package com.recipe.roulette.app.injection.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.bumptech.glide.RequestManager;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.api.Food2ForkApi;
import com.recipe.roulette.app.injection.module.AppModule;
import com.recipe.roulette.app.injection.module.Food2ForkAPIModule;
import com.recipe.roulette.app.injection.module.NetworkModule;
import com.recipe.roulette.app.view.fragment.GenericSwipeCardFragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, Food2ForkAPIModule.class})
public interface AppComponent {

    Context getAppContext();

    RecipeRouletteApplication getApp();

    @Named("f2f")
    Retrofit retrofit();

    @Named("reddit")
    Retrofit retrofit_r();

    @Named("reddit_oauth")
    Retrofit retrofit_ro();


    @Named("f2f")
    OkHttpClient okHttpClient();

    @Named("reddit")
    OkHttpClient okHttpClient_r();

    @Named("reddit_oauth")
    OkHttpClient okHttpClient_ro();

    SharedPreferences sharedPreferences();

    Food2ForkApi food2ForkApi();

    RequestManager glide();

    void inject(RecipeRouletteApplication recipeRouletteApplication);
    void inject(GenericSwipeCardFragment fragment);
}