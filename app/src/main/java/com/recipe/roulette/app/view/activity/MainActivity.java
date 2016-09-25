package com.recipe.roulette.app.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.helpers.ChangeFragmentHelper;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerMain2ViewComponent;
import com.recipe.roulette.app.injection.module.Main2ViewModule;
import com.recipe.roulette.app.presenter.Main2Presenter;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.view.Main2View;
import com.recipe.roulette.app.view.impl.BaseActivity;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity<Main2Presenter, Main2View> implements Main2View {

    @Inject
    PresenterFactory<Main2Presenter> mPresenterFactory;
    // Your presenter is available using the mPresenter variable

    @Inject
    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("H", "Hello world");
        editor.apply();
        ChangeFragmentHelper.setMainFragment(this, R.id.main_fragment);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMain2ViewComponent.builder()
                .appComponent(parentComponent)
                .main2ViewModule(new Main2ViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<Main2Presenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
