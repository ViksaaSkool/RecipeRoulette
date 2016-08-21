package com.recipe.roulette.app.injection;

import com.recipe.roulette.app.view.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = Main2ViewModule.class)
public interface Main2ViewComponent {
    void inject(MainActivity activity);
}