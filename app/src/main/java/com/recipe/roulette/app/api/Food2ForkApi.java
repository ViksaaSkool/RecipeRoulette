package com.recipe.roulette.app.api;

import com.recipe.roulette.app.BuildConfig;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.injection.module.Food2ForkAPIModule;
import com.recipe.roulette.app.model.RecipesSearchResponse;
import com.recipe.roulette.app.util.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by varsovski on 25-Sep-16.
 */

public class Food2ForkApi {

    //retrofit call
    private Food2ForkAPIModule.Food2ForkApiInterface mFood2ForkApiInterface;
    private Call mCall;

    public Food2ForkApi(Food2ForkAPIModule.Food2ForkApiInterface food2ForkApiInterface){
        this.mFood2ForkApiInterface = food2ForkApiInterface;
    }

    /* Service calls */
    @SuppressWarnings("all")
    public void searchForRecipes(String query) {
        //if call is running, stop it and call it again
        cancelCurrentCall();
        mCall = mFood2ForkApiInterface.searchForRecipes(BuildConfig.FOOD_2_FORK_API_KEY, query);
        mCall.clone().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccess()) {
                    LogUtil.d(Constants.API_TAG, "searchForRecipes() | SUCCESS!");

                    RecipesSearchResponse results = (RecipesSearchResponse) response.body();



                } else {
                    LogUtil.d(Constants.API_TAG, "searchForRecipes() | SUCCESS! BUT, response.isSuccess() == false");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                LogUtil.e(Constants.API_TAG, "searchForRecipes() | ERROR! t = " + t.getMessage());
            }
        });
    }


    public void cancelCurrentCall() {
        if (mCall != null) {
            mCall.cancel();
            LogUtil.e(Constants.API_TAG, "cancelCurrentCall() | canceled!");
        }

    }
}
