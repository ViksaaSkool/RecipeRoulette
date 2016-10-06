package com.recipe.roulette.app.injection.module;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.RecipeSwipeInteractor;
import com.recipe.roulette.app.interactor.impl.RecipeSwipeInteractorImpl;
import com.recipe.roulette.app.presenter.RecipeSwipePresenter;
import com.recipe.roulette.app.presenter.impl.RecipeSwipePresenterImpl;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class RecipeSwipeViewModule {
    @Provides
    public RecipeSwipeInteractor provideInteractor() {
        return new RecipeSwipeInteractorImpl();
    }

    @Provides
    public PresenterFactory<RecipeSwipePresenter> providePresenterFactory(@NonNull final RecipeSwipeInteractor interactor) {
        return new PresenterFactory<RecipeSwipePresenter>() {
            @NonNull
            @Override
            public RecipeSwipePresenter create() {
                return new RecipeSwipePresenterImpl(interactor);
            }
        };
    }


}
