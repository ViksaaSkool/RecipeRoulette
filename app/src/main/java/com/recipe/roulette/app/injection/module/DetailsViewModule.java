package com.recipe.roulette.app.injection.module;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.DetailsInteractor;
import com.recipe.roulette.app.interactor.impl.DetailsInteractorImpl;
import com.recipe.roulette.app.presenter.DetailsPresenter;
import com.recipe.roulette.app.presenter.impl.DetailsPresenterImpl;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class DetailsViewModule {
    @Provides
    public DetailsInteractor provideInteractor() {
        return new DetailsInteractorImpl();
    }

    @Provides
    public PresenterFactory<DetailsPresenter> providePresenterFactory(@NonNull final DetailsInteractor interactor) {

        return new PresenterFactory<DetailsPresenter>() {
            @NonNull
            @Override
            public DetailsPresenter create() {
                return new DetailsPresenterImpl(interactor);
            }
        };
    }
}
