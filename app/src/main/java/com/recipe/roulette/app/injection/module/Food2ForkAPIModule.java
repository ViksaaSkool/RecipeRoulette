package com.recipe.roulette.app.injection.module;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.RecipesSearchResponse;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by varsovski on 25-Sep-16.
 */

@Module
public class Food2ForkAPIModule {

    public interface Food2ForkApiInterface {
        @GET(Constants.SEARCH_RECIPES)
        Call<RecipesSearchResponse> searchForRecipes(@Query(Constants.KEY_PARAM) String key,
                                                     @Query(Constants.QUERY_PARAM) String query);
    }

    @Provides
    @Singleton // needs to be consistent with the component scope
    public Food2ForkApiInterface providesFood2ForkApiInterface(Retrofit retrofit) {
        return retrofit.create(Food2ForkApiInterface.class);
    }

}
