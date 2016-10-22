package com.recipe.roulette.app.api;

import com.recipe.roulette.app.injection.module.RedditModule;
import com.recipe.roulette.app.model.RecipesSearchResponse;

import retrofit2.Call;

/**
 * Created by varsovski on 10-Oct-16.
 */

public class RedditApi {

    //retrofit call
    private RedditModule.RedditOauthModuleApiInterface mRedditOauthModuleApiInterface;
    private Call mCall;

    private RecipesSearchResponse mSearchResults;

    public RedditApi(RedditModule.RedditOauthModuleApiInterface redditOauthModuleApiInterface){
        this.mRedditOauthModuleApiInterface = redditOauthModuleApiInterface;
    }


    /*
    * String bodyString = BuildConfig.REDDIT_BODY_PARAMS + CacheUtil.getDID();
        TypedInput requestBody = new TypedByteArray("application/x-www-form-urlencoded", bodyString.getBytes(Charset.forName("UTF-8")));
        */
}
