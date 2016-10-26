package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.DetailsViewModule;
import com.recipe.roulette.app.injection.scope.ActivityScope;
import com.recipe.roulette.app.view.activity.DetailsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = DetailsViewModule.class)
public interface DetailsViewComponent {
    void inject(DetailsActivity activity);
}