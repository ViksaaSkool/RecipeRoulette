package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.CustomViewModule;
import com.recipe.roulette.app.injection.scope.FragmentScope;
import com.recipe.roulette.app.view.fragment.MainFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = CustomViewModule.class)
public interface CustomViewComponent {
    void inject(MainFragment fragment);
}