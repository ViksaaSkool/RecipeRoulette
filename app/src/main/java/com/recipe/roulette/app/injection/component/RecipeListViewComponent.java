package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.RecipeListViewModule;
import com.recipe.roulette.app.injection.scope.FragmentScope;
import com.recipe.roulette.app.view.fragment.RecipeListFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = RecipeListViewModule.class)
public interface RecipeListViewComponent {
    void inject(RecipeListFragment fragment);
}