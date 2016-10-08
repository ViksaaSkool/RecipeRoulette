package com.recipe.roulette.app.presenter;

import com.recipe.roulette.app.view.CustomView;

public interface CustomPresenter extends BasePresenter<CustomView> {

    void search(String query);


}