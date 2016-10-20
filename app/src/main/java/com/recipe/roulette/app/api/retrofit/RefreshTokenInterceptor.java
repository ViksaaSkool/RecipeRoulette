package com.recipe.roulette.app.api.retrofit;

import android.content.SharedPreferences;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by varsovski on 20-Oct-16.
 */

public class RefreshTokenInterceptor implements Interceptor {

    private SharedPreferences mSharedPreferences;

    public RefreshTokenInterceptor(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // try the request
        Response response = chain.proceed(request);

        if (!response.isSuccessful()) {
            LogUtil.d(Constants.API_TAG, "RefreshTokenInterceptor intercept()| Token needs to be refreshed!");
            // get a new token (I use a synchronous Retrofit call)


            // create a new request and modify it accordingly using the new token
            Request newRequest = request.newBuilder().build();

            // retry the request
            return chain.proceed(newRequest);
        }

        LogUtil.d(Constants.API_TAG, "RefreshTokenInterceptor intercept()| Token doesn't need to be refreshed! All cool");

        return response;
    }
}
