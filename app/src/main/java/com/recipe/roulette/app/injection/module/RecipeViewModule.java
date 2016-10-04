package com.recipe.roulette.app.injection.module;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.RecipeInteractor;
import com.recipe.roulette.app.interactor.impl.RecipeInteractorImpl;
import com.recipe.roulette.app.presenter.RecipePresenter;
import com.recipe.roulette.app.presenter.impl.RecipePresenterImpl;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class RecipeViewModule {
    @Provides
    public RecipeInteractor provideInteractor() {
        return new RecipeInteractorImpl();
    }

    @Provides
    public PresenterFactory<RecipePresenter> providePresenterFactory(@NonNull final RecipeInteractor interactor) {
        return new PresenterFactory<RecipePresenter>() {
            @NonNull
            @Override
            public RecipePresenter create() {
                return new RecipePresenterImpl(interactor);
            }
        };
    }
}
