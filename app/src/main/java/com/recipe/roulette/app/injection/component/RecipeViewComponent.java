package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.RecipeViewModule;
import com.recipe.roulette.app.injection.scope.FragmentScope;
import com.recipe.roulette.app.view.fragment.RecipeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = RecipeViewModule.class)
public interface RecipeViewComponent {
    void inject(RecipeFragment fragment);
}