package com.recipe.roulette.app.view.activity;

import com.recipe.roulette.app.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.model.ConfigSplash;

/**
 * Created by varsovski on 26-May-16.
 */
public class SplashScreenActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.colorPrimary);
        configSplash.setLogoSplash(R.drawable.splash_logo);

        configSplash.setTitleSplash("");
        configSplash.setAnimTitleDuration(0);
    }

    @Override
    public void animationsFinished() {

    }
}
