package com.recipe.roulette.app.injection;

import com.recipe.roulette.app.view.fragment.MainFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = CustomViewModule.class)
public interface CustomViewComponent {
    void inject(MainFragment fragment);
}