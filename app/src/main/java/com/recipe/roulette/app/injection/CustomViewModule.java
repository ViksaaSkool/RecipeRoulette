package com.recipe.roulette.app.injection;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.CustomInteractor;
import com.recipe.roulette.app.interactor.impl.CustomInteractorImpl;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.presenter.CustomPresenter;
import com.recipe.roulette.app.presenter.impl.CustomPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class CustomViewModule {
    @Provides
    public CustomInteractor provideInteractor() {
        return new CustomInteractorImpl();
    }

    @Provides
    public PresenterFactory<CustomPresenter> providePresenterFactory(@NonNull final CustomInteractor interactor) {
        return new PresenterFactory<CustomPresenter>() {
            @NonNull
            @Override
            public CustomPresenter create() {
                return new CustomPresenterImpl(interactor);
            }
        };
    }
}
