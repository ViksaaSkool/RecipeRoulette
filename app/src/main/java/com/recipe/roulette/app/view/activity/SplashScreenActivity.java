package com.recipe.roulette.app.view.activity;

import android.content.SharedPreferences;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.helpers.ChangeActivityHelper;
import com.recipe.roulette.app.injection.module.RedditModule;
import com.recipe.roulette.app.model.reddit.TokenResponse;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.util.RedditApiUtil;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by varsovski on 26-May-16.
 */
public class SplashScreenActivity extends AwesomeSplash {

    @Inject
    SharedPreferences mSharedPreferneces;

    @Inject
    RedditModule.RedditOauthModuleApiInterface mRedditOauthModuleApiInterface;

    private boolean isTokenRetreived = false;
    private boolean hasAnimationFinished = false;

    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.colorPrimary);
        configSplash.setLogoSplash(R.drawable.splash_logo);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);

        configSplash.setTitleSplash("");
        configSplash.setAnimTitleDuration(0);

        RecipeRouletteApplication.getAppComponent().inject(this);
        getToken();
    }

    @Override
    public void animationsFinished() {
        hasAnimationFinished = true;
        if (isTokenRetreived)
            ChangeActivityHelper.changeActivity(this, MainActivity.class, true);
    }


    public void getToken() {
        if (RedditApiUtil.hasValidToken(mSharedPreferneces)) {

            Observable<TokenResponse> call = mRedditOauthModuleApiInterface.oAuth(RedditApiUtil.getReqestBodyForToken());
            call.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<TokenResponse>() {
                        @Override
                        public void onCompleted() {
                            LogUtil.d(Constants.API_TAG, "getToken() | call completed!");
                            isTokenRetreived = true;
                            if (hasAnimationFinished)
                                animationsFinished();
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e(Constants.API_TAG, " getToken() | e = " + e.getMessage());

                        }

                        @Override
                        public void onNext(TokenResponse tokenResponse) {
                            if (tokenResponse != null)
                                RedditApiUtil.saveTokenSharedPreferences(mSharedPreferneces, tokenResponse);
                            else
                                LogUtil.d(Constants.API_TAG, "getToken() | tokenResponse is NULL!?");
                        }
                    });

        } else
            isTokenRetreived = true;
    }


}
