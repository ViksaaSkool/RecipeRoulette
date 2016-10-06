package com.recipe.roulette.app.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends BaseActivity<Main2Presenter, Main2View> implements Main2View {

    @Inject
    PresenterFactory<Main2Presenter> mPresenterFactory;
    // Your presenter is available using the mPresenter variable

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.root_layout)
    CoordinatorLayout mRootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        setToolbar(getString(R.string.app_name));
        ChangeFragmentHelper.setMainFragment(this, R.id.main_fragment);
    }

    @Override
    protected void onStart() {
        super.onStart();

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

    @Override
    public void setToolbar(String title) {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    @Override
    public void showSnackbarNotification(String text, int duration) {
        if (mRootLayout != null)
            Snackbar.make(mRootLayout, text, duration).show();
    }

    @Override
    public void onConnectionChange(boolean isConnected) {
        if (!isConnected)
            showSnackbarNotification(getString(R.string.notification_no_connection), Snackbar.LENGTH_SHORT);
    }


}
