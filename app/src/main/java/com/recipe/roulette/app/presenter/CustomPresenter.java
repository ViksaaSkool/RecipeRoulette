package com.recipe.roulette.app.presenter;

import android.widget.CompoundButton;

import com.recipe.roulette.app.view.CustomView;

public interface CustomPresenter extends BasePresenter<CustomView> {

    void search(String query);

    void setRecipeView(CompoundButton buttonView, boolean isChecked);


}