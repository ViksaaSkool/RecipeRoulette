package com.recipe.roulette.app.adapters.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by varsovski on 06-Oct-16.
 */

public class RecipeSwipeViewPagerAdapter extends FragmentStatePagerAdapter {


    public RecipeSwipeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
