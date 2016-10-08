package com.recipe.roulette.app.util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.recipe.roulette.app.RecipeRouletteApplication;

/**
 * Created by varsovski on 04-Oct-16.
 */

public class UIUtil {

    public static void hideKeyboard(View view) {
        // Check if no view has focus:
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) RecipeRouletteApplication.getAppComponent().getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideSoftKeyOutsideET(View view) {

        // if the view is not instance of AutoResizeEditText
        // i.e. if the user taps outside of the box
        if (!((view instanceof EditText)
                || (view instanceof SearchView))) {

            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //hide the keyboard
                    hideKeyboard(v);
                    return false;
                }
            });
        }
        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideSoftKeyOutsideET(innerView);
            }
        }
    }

    public static int containerHeight(AppCompatActivity a, int ratio) {
        DisplayMetrics dm = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return (dm.heightPixels / ratio);
    }
}
