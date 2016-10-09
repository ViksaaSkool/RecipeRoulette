package com.recipe.roulette.app.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;

/**
 * Created by varsovski on 21-Aug-16.
 */
public class ShareUtil {

    public static void shareRecipe(Context c, String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        c.startActivity(Intent.createChooser(intent, c.getResources().getString(R.string.title_share)));
    }

    public static void openLink(String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        RecipeRouletteApplication.getAppComponent().getAppContext().startActivity(browserIntent);
    }
}
