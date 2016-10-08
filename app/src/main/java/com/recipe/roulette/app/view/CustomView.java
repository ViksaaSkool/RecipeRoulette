package com.recipe.roulette.app.view;

import android.support.annotation.UiThread;

@UiThread
public interface CustomView {

    void onSearchResultsReady(int count);

    void loading(boolean showLoading);

}