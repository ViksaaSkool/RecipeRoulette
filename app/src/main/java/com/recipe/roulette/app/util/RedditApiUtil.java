package com.recipe.roulette.app.util;

import android.content.SharedPreferences;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.reddit.ChildData;
import com.recipe.roulette.app.model.reddit.TokenResponse;

import java.nio.charset.Charset;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by varsovski on 10-Oct-16.
 */

public class RedditApiUtil {

    private static final String YOUTUBE = "youtube";
    private static final String GIF = ".gif";
    private static final String GIFV = ".gifv";
    private static final String MP4 = ".mp4";

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static int getRecipeType(ChildData dataItem) {
        if (dataItem.getDomain() != null && dataItem.getDomain().contains(YOUTUBE))
            return Constants.VIDEO;
        else if (dataItem.getUrl() != null
                && (dataItem.getUrl().contains(GIF) || dataItem.getUrl().contains(GIFV)))
            return Constants.GIF;
        else
            return -1;
    }

    public static boolean isVideoOrGif(ChildData dataItem) {
        return getRecipeType(dataItem) != -1;
    }

    public static String getProperUrlFormat(String url) {
        if (url.contains(GIFV))
            return url.replace(GIFV, GIF);
        else
            return url;
    }

    public static RequestBody getReqestBodyForToken() {
        String bodyString = Constants.BODY_PARAMS_GET_TOKEN + RedditApiUtil.getUUID();
        RequestBody r = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                bodyString.getBytes(Charset.forName("UTF-8")));
        return r;
    }

    public static boolean hasValidToken(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(Constants.ACCESS_TOKEN, "").isEmpty()
                || sharedPreferences.getLong(Constants.TOKEN_EXPIRATION_TIME, System.currentTimeMillis()) <= System.currentTimeMillis();
    }

    public static void saveTokenSharedPreferences(SharedPreferences sharedPreferences, TokenResponse tokenResponse) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (tokenResponse.getAccessToken() != null && tokenResponse.getTokenType() != null) {
            String token = tokenResponse.getTokenType() + " " + tokenResponse.getAccessToken();
            LogUtil.d(Constants.API_TAG, "saveTokenSharedPreferences() | token = " + token);
            editor.putString(Constants.ACCESS_TOKEN, token).apply();
        }
        if (tokenResponse.getExpiresIn() != null) {
            Long expirationTime = System.currentTimeMillis() + Long.valueOf(tokenResponse.getExpiresIn()) * 1000;
            editor.putLong(Constants.TOKEN_EXPIRATION_TIME, expirationTime).apply();
        }

    }


}
