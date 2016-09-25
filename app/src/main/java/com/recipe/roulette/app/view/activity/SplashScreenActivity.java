package com.recipe.roulette.app.view.activity;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.helpers.ChangeActivityHelper;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

/**
 * Created by varsovski on 26-May-16.
 */
public class SplashScreenActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.colorPrimary);
        configSplash.setLogoSplash(R.drawable.splash_logo);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);

        configSplash.setTitleSplash("");
        configSplash.setAnimTitleDuration(0);
    }

    @Override
    public void animationsFinished() {

        //set the main activity
        ChangeActivityHelper.changeActivity(this, MainActivity.class, true);
    }
}
