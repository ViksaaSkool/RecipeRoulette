package com.recipe.roulette.app.util;

import android.util.Log;

import com.recipe.roulette.app.BuildConfig;

/**
 * Created by varsovski on 21-Aug-16.
 */
public class LogUtil {

    public static void dLog(String TAG, String message) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, message);
    }

    public static void eLog(String TAG, String message) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, message);
    }

    public static void wLog(String TAG, String message) {
        if (BuildConfig.DEBUG)
            Log.w(TAG, message);
    }

    public static void iLog(String TAG, String message) {
        if (BuildConfig.DEBUG)
            Log.i(TAG, message);
    }
}
