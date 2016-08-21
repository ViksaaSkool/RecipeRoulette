package com.recipe.roulette.app.injection.module;

import android.support.annotation.NonNull;

import com.recipe.roulette.app.interactor.Main2Interactor;
import com.recipe.roulette.app.interactor.impl.Main2InteractorImpl;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.presenter.Main2Presenter;
import com.recipe.roulette.app.presenter.impl.Main2PresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class Main2ViewModule {
    @Provides
    public Main2Interactor provideInteractor() {
        return new Main2InteractorImpl();
    }

    @Provides
    public PresenterFactory<Main2Presenter> providePresenterFactory(@NonNull final Main2Interactor interactor) {
        return new PresenterFactory<Main2Presenter>() {
            @NonNull
            @Override
            public Main2Presenter create() {
                return new Main2PresenterImpl(interactor);
            }
        };
    }
}
