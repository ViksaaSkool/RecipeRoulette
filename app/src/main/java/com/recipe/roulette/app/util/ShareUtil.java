package com.recipe.roulette.app.util;

import android.content.Context;
import android.content.Intent;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;

/**
 * Created by varsovski on 21-Aug-16.
 */
public class ShareUtil {

    public static void shareRecipe(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        Context c = RecipeRouletteApplication.getAppComponent().getAppContext();
        c.startActivity(Intent.createChooser(intent, c.getResources().getString(R.string.title_share)));
    }
}
