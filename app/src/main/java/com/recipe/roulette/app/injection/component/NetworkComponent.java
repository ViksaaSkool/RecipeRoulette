package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by varsovski on 24-Sep-16.
 */

@Singleton
@Component(/*dependencies = AppComponent.class,*/ modules = NetworkModule.class)
public interface NetworkComponent {
   // void inject(MainActivity activity);
}
