package com.recipe.roulette.app.presenter;

/**
 * Created by varsovski on 08-Oct-16.
 */

public interface ParentPresenterOwner<T extends BasePresenter> {

    T getParentPresenter();
}

