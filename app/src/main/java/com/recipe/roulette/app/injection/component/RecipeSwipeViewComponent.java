package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.RecipeSwipeViewModule;
import com.recipe.roulette.app.injection.scope.FragmentScope;
import com.recipe.roulette.app.view.fragment.RecipeSwipeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = RecipeSwipeViewModule.class)
public interface RecipeSwipeViewComponent {
    void inject(RecipeSwipeFragment fragment);
}