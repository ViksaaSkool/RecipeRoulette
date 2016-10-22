package com.recipe.roulette.app.api.retrofit;

import android.content.SharedPreferences;

import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.injection.module.RedditModule;
import com.recipe.roulette.app.model.reddit.TokenResponse;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.util.RedditApiUtil;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

/**
 * Created by varsovski on 20-Oct-16.
 */

public class RefreshTokenInterceptor implements Interceptor {

    @Inject
    private SharedPreferences mSharedPreferences;

    @Inject
    private RedditModule.RedditOauthModuleApiInterface mRedditOauthModuleApiInterface;

    public RefreshTokenInterceptor() {
        RecipeRouletteApplication.getAppComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // try the request
        Response response = chain.proceed(request);

        if (!response.isSuccessful()) {
            LogUtil.d(Constants.API_TAG, "RefreshTokenInterceptor intercept()| Token needs to be refreshed!");

            // get a new token (I use a synchronous Retrofit call)
            Call<TokenResponse> tokenCall = mRedditOauthModuleApiInterface.refreshToken(RedditApiUtil.getReqestBodyForToken());
            TokenResponse token = tokenCall.execute().body();
            RedditApiUtil.saveTokenSharedPreferences(mSharedPreferences, token);

            // create a new request and modify it accordingly using the new token
            Request newRequest = request.newBuilder().header("Authorization", token.getAccessToken()).build();

            // retry the request
            return chain.proceed(newRequest);
        }

        LogUtil.d(Constants.API_TAG, "RefreshTokenInterceptor intercept()| Token doesn't need to be refreshed! All cool");

        return response;
    }
}
