package com.recipe.roulette.app.injection.module;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.RecipesSearchResponse;
import com.recipe.roulette.app.model.reddit.TokenResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by varsovski on 11-Oct-16.
 */

public class RedditModule {

    public interface RedditOauthModuleApiInterface {
        @GET(Constants.REDDIT_OATH2_PATH)
        Call<TokenResponse> oAuth(@Query(Constants.KEY_PARAM) String key,
                                  @Query(Constants.QUERY_PARAM) String query);
    }
    public interface RedditModuleApiInterface {
        @GET(Constants.SEARCH_SUBREDDIT)
        Call<RecipesSearchResponse> searchSubreddit(@Query(Constants.KEY_PARAM) String key,
                                          @Query(Constants.QUERY_PARAM) String query);


    }

    /*@Provides
    @Singleton // needs to be consistent with the component scope
    public Food2ForkAPIModule.Food2ForkApiInterface providesFood2ForkApiInterface(@Named("reddit") Retrofit retrofit) {
        return retrofit.create(Food2ForkAPIModule.Food2ForkApiInterface.class);
    }


    @Provides
    @Singleton // needs to be consistent with the component scope
    public Food2ForkApi providesFood2ForkApi(Food2ForkAPIModule.Food2ForkApiInterface food2ForkApiInterface) {
        return new Food2ForkApi(food2ForkApiInterface);
    }*/
}
