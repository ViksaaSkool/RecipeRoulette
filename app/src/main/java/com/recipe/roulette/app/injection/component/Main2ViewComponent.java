package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.Main2ViewModule;
import com.recipe.roulette.app.injection.scope.ActivityScope;
import com.recipe.roulette.app.view.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = Main2ViewModule.class)
public interface Main2ViewComponent {
    void inject(MainActivity activity);
}