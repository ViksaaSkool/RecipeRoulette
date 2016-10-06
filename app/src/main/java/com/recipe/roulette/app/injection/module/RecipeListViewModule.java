package com.recipe.roulette.app.injection.module;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.RecipeListInteractor;
import com.recipe.roulette.app.interactor.impl.RecipeListInteractorImpl;
import com.recipe.roulette.app.presenter.RecipeListPresenter;
import com.recipe.roulette.app.presenter.impl.RecipeListPresenterImpl;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class RecipeListViewModule {
    @Provides
    public RecipeListInteractor provideInteractor() {
        return new RecipeListInteractorImpl();
    }

    @Provides
    public PresenterFactory<RecipeListPresenter> providePresenterFactory(@NonNull final RecipeListInteractor interactor) {
        return new PresenterFactory<RecipeListPresenter>() {
            @NonNull
            @Override
            public RecipeListPresenter create() {
                return new RecipeListPresenterImpl(interactor);
            }
        };
    }
}
