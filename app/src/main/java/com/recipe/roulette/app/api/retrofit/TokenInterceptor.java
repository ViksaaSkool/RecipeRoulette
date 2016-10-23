package com.recipe.roulette.app.api.retrofit;

import android.content.SharedPreferences;

import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.injection.module.RedditModule;
import com.recipe.roulette.app.util.LogUtil;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by varsovski on 23-Oct-16.
 */

public class TokenInterceptor implements Interceptor {


    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    RedditModule.RedditOauthModuleApiInterface mRedditOauthModuleApiInterface;

    public TokenInterceptor() {
        RecipeRouletteApplication.getAppComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        String token = mSharedPreferences.getString(Constants.ACCESS_TOKEN, "");
        LogUtil.d(Constants.API_TAG, "intercept() | token = " + token);

        /*if (authorizationTokenIsEmpty() || alreadyHasAuthorizationHeader(originalRequest)) {
            return chain.proceed(originalRequest);
        }*/

            // Add authorization header with updated authorization value to  intercepted request
        Request authorisedRequest = originalRequest.newBuilder()
                .header("Authorization", token)
                .build();
        return chain.proceed(authorisedRequest);
    }
}
