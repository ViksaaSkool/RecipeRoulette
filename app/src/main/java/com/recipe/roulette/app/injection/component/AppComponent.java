package com.recipe.roulette.app.injection.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.bumptech.glide.RequestManager;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.api.Food2ForkApi;
import com.recipe.roulette.app.api.RedditApi;
import com.recipe.roulette.app.api.retrofit.RefreshTokenInterceptor;
import com.recipe.roulette.app.injection.module.AppModule;
import com.recipe.roulette.app.injection.module.Food2ForkAPIModule;
import com.recipe.roulette.app.injection.module.NetworkModule;
import com.recipe.roulette.app.injection.module.RedditModule;
import com.recipe.roulette.app.interactor.impl.BaseInteractiorImpl;
import com.recipe.roulette.app.view.activity.SplashScreenActivity;
import com.recipe.roulette.app.view.fragment.GenericSwipeCardFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, Food2ForkAPIModule.class, RedditModule.class})
public interface AppComponent {

    Context getAppContext();

    RecipeRouletteApplication getApp();

    SharedPreferences sharedPreferences();

    Food2ForkApi food2ForkApi();

    RedditApi redditApi();

    RequestManager glide();

    RedditModule.RedditOauthModuleApiInterface redditOauthApiInterface();

    RedditModule.RedditModuleApiInterface redditModuleApiInterface();

    void inject(RecipeRouletteApplication recipeRouletteApplication);

    void inject(GenericSwipeCardFragment fragment);

    void inject(SplashScreenActivity activity);

    void inject(RefreshTokenInterceptor refreshTokenInterceptor);

    void inject(BaseInteractiorImpl baseInteractorImpl);
}