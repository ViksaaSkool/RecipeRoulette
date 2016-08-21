package com.recipe.roulette.app.injection.component;

import com.recipe.roulette.app.injection.module.AppModule;
import com.recipe.roulette.app.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by varsovski on 21-Aug-16.
 */

@Singleton
@Component(modules={AppModule.class, NetworkModule.class})
public interface NetworkComponent {
}
