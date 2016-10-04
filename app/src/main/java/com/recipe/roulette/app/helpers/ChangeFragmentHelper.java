package com.recipe.roulette.app.helpers;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.recipe.roulette.app.view.fragment.MainFragment;
import com.recipe.roulette.app.view.fragment.RecipeFragment;

/**
 * Created by varsovski on 21-Aug-16.
 */
public class ChangeFragmentHelper {

    public static final String MAIN_FRAGMENT = "mainFragment";
    public static final String RECIPE_FRAGMENT = "recipeFragment";


    public static void setMainFragment(AppCompatActivity a, int container) {
        FragmentTransaction ft = a.getSupportFragmentManager().beginTransaction();
        MainFragment fragment = MainFragment.newInstance();
        ft.replace(container, fragment, MAIN_FRAGMENT);
        ft.commit();
    }

    public static void setRecipeFragment(AppCompatActivity a, int container) {
        FragmentTransaction ft = a.getSupportFragmentManager().beginTransaction();
        RecipeFragment fragment = RecipeFragment.newInstance();
        ft.replace(container, fragment, RECIPE_FRAGMENT);
        ft.commit();
    }
}
