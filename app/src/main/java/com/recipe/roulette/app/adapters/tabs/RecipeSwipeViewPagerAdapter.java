package com.recipe.roulette.app.adapters.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.recipe.roulette.app.view.fragment.GenericSwipeCardFragment;

/**
 * Created by varsovski on 06-Oct-16.
 */

public class RecipeSwipeViewPagerAdapter extends FragmentStatePagerAdapter {

    private int recipeCount = 0;

    public RecipeSwipeViewPagerAdapter(FragmentManager fm, int rc) {
        super(fm);
        this.recipeCount = rc;
    }

    @Override
    public Fragment getItem(int position) {
        return GenericSwipeCardFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return recipeCount;
    }
}
