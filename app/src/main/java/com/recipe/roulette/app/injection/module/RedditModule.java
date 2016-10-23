package com.recipe.roulette.app.injection.module;

import com.recipe.roulette.app.api.RedditApi;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.reddit.RedditRecipeResponse;
import com.recipe.roulette.app.model.reddit.TokenResponse;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by varsovski on 11-Oct-16.
 */
@Module
public class RedditModule {

    public interface RedditOauthModuleApiInterface {
        @POST(Constants.REDDIT_OATH2_PATH)
        Observable<TokenResponse> oAuth(@Body RequestBody body);

        //has to be done synchronously, no need for observable
        @POST(Constants.REDDIT_OATH2_PATH)
        Call<TokenResponse> refreshToken(@Body RequestBody body);
    }

    public interface RedditModuleApiInterface {
        @GET(Constants.SEARCH_SUBREDDIT)
        Observable<RedditRecipeResponse> searchSubreddit(@Path(Constants.SUBREDDIT_PATH) String subreddit,
                                                         @Query(Constants.QUERY_PARAM) String query,
                                                         @Query(Constants.RESTRICT_PARAM) String restrict,
                                                         @Query(Constants.SORT_PARAM) String sort,
                                                         @Query(Constants.SCOPE_PARAM) String scope);
    }

    @Provides
    @Singleton // needs to be consistent with the component scope
    public RedditModule.RedditOauthModuleApiInterface providesRedditOauthModuleApiInterface(@Named("reddit_oauth") Retrofit retrofit) {
        return retrofit.create(RedditModule.RedditOauthModuleApiInterface.class);
    }

    @Provides
    @Singleton // needs to be consistent with the component scope
    public RedditModule.RedditModuleApiInterface providesRedditModuleApiInterface(@Named("reddit") Retrofit retrofit) {
        return retrofit.create(RedditModule.RedditModuleApiInterface.class);
    }

    @Provides
    @Singleton
    public RedditApi providesRedditApi() {
        return new RedditApi();
    }
}
