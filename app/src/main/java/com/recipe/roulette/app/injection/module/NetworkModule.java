package com.recipe.roulette.app.injection.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.recipe.roulette.app.BuildConfig;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by varsovski on 24-Sep-16.
 */

@Module
public final class NetworkModule {

    // private String mAccessToken = "";

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
    @Named("f2f")
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient().newBuilder().cache(cache).build();
    }

    @Provides
    @Named("reddit")
    @Singleton
    OkHttpClient provideOkHttpClient_r(Cache cache, final SharedPreferences sharedPreferences) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String token = sharedPreferences.getString(Constants.ACCESS_TOKEN, "");
                return response.request().newBuilder().header("Authorization", token).build();
            }
        });
        okHttpClient.cache(cache);

        return okHttpClient.build();
    }

    @Provides
    @Named("reddit_oauth")
    @Singleton
    OkHttpClient provideOkHttpClient_ro(Cache cache, SharedPreferences sharedPreferences) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(BuildConfig.REDDIT_CLIENT_ID, BuildConfig.REDDIT_PASS);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        });
        //if token is expired and it need to be refreshed
        // okHttpClient.addInterceptor(new RefreshTokenInterceptor(sharedPreferences));
        okHttpClient.cache(cache);

        return okHttpClient.build();
    }

    @Provides
    @Named("f2f")
    @Singleton
    Retrofit provideRetrofit_f2f(Gson gson, @Named("f2f") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_F2F_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Named("reddit")
    @Singleton
    Retrofit provideRetrofit_r(Gson gson, @Named("reddit") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_REDDIT_URL)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Named("reddit_oauth")
    @Singleton
    Retrofit provideRetrofit_ro(Gson gson, @Named("reddit_oauth") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_OAUTH_REDDIT_URL)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    RequestManager provideGlide(RecipeRouletteApplication application) {
        return Glide.with(application);
    }

}
