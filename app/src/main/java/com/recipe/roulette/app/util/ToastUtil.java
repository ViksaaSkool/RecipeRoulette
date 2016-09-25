package com.recipe.roulette.app.util;

import android.widget.Toast;

import com.recipe.roulette.app.BuildConfig;
import com.recipe.roulette.app.RecipeRouletteApplication;

/**
 * Created by varsovski on 26-Sep-16.
 */

public class ToastUtil {

    public static void showToast(String message, int length) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(RecipeRouletteApplication.getAppComponent().getApp(), message, length).show();
        }
    }
}
