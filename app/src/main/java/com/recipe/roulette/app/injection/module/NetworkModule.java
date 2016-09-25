package com.recipe.roulette.app.injection.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by varsovski on 24-Sep-16.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(RecipeRouletteApplication application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(RecipeRouletteApplication application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient().newBuilder().cache(cache).build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_F2F_URL)
                .client(okHttpClient)
                .build();
    }


    //API Calls

    /*public interface Food2ForkApiInterface {
        @GET(Constants.SEARCH_RECIPES)
        Call<ArrayList<Recipe>> searchForRecipes(@Field(Constants.KEY_PARAM) String key,
                                                 @Field(Constants.QUERY_PARAM) String query);
    }

    @Provides
    @PerApp // needs to be consistent with the component scope
    public Food2ForkApiInterface providesFood2ForkApiInterface(Retrofit retrofit) {
        return retrofit.create(Food2ForkApiInterface.class);
    }*/
}
