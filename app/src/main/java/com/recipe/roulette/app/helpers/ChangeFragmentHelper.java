package com.recipe.roulette.app.helpers;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.recipe.roulette.app.view.fragment.MainFragment;
import com.recipe.roulette.app.view.fragment.RecipeListFragment;
import com.recipe.roulette.app.view.fragment.RecipeSwipeFragment;

/**
 * Created by varsovski on 21-Aug-16.
 */
public class ChangeFragmentHelper {

    public static final String MAIN_FRAGMENT = "mainFragment";
    public static final String RECIPE_LIST_FRAGMENT = "recipeListFragment";
    public static final String RECIPE_SWIPE_FRAGMENT = "recipeSwipeFragment";


    public static void setMainFragment(AppCompatActivity a, int container) {
        FragmentTransaction ft = a.getSupportFragmentManager().beginTransaction();
        MainFragment fragment = MainFragment.newInstance();
        ft.replace(container, fragment, MAIN_FRAGMENT);
        ft.commit();
    }

    public static void setRecipeListFragment(AppCompatActivity a, int container) {
        FragmentTransaction ft = a.getSupportFragmentManager().beginTransaction();
        RecipeListFragment fragment = RecipeListFragment.newInstance();
        ft.replace(container, fragment, RECIPE_LIST_FRAGMENT);
        ft.commit();
    }

    public static void setRecipeSwipeFragment(AppCompatActivity a, int container) {
        FragmentTransaction ft = a.getSupportFragmentManager().beginTransaction();
        RecipeSwipeFragment fragment = RecipeSwipeFragment.newInstance();
        ft.replace(container, fragment, RECIPE_SWIPE_FRAGMENT);
        ft.commit();
    }
}
