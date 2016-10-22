package com.recipe.roulette.app.injection.module;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.reddit.RedditRecipeResponse;
import com.recipe.roulette.app.model.reddit.TokenResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by varsovski on 11-Oct-16.
 */

public class RedditModule {

    public interface RedditOauthModuleApiInterface {
        @GET(Constants.REDDIT_OATH2_PATH)
        Observable<TokenResponse> oAuth(@Body RequestBody body);
    }

    public interface RedditModuleApiInterface {
        @GET(Constants.SEARCH_SUBREDDIT)
        Observable<RedditRecipeResponse> searchSubreddit(@Query(Constants.QUERY_PARAM) String query,
                                                         @Query(Constants.RESTRICT_PARAM) String restrict,
                                                         @Query(Constants.SORT_PARAM) String sort,
                                                         @Query(Constants.SCOPE_PARAM) String scope);
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
