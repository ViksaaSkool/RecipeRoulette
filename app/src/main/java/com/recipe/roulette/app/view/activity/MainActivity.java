package com.recipe.roulette.app.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.helpers.ChangeFragmentHelper;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerMain2ViewComponent;
import com.recipe.roulette.app.injection.module.Main2ViewModule;
import com.recipe.roulette.app.presenter.Main2Presenter;
import com.recipe.roulette.app.presenter.ParentPresenterOwner;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.view.Main2View;
import com.recipe.roulette.app.view.impl.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.recipe.roulette.app.R.id.appbar;

public final class MainActivity extends BaseActivity<Main2Presenter, Main2View> implements Main2View, ParentPresenterOwner<Main2Presenter> {

    @Inject
    PresenterFactory<Main2Presenter> mPresenterFactory;
    // Your presenter is available using the mPresenter variable

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.root_layout)
    CoordinatorLayout mRootLayout;
    @BindView(appbar)
    AppBarLayout mAppbar;


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

    @Override
    public void setToolbar(String title) {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    @Override
    public void setBackButton(boolean hasBackButton) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton);
            getSupportActionBar().setDisplayShowHomeEnabled(hasBackButton);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void showSnackbarNotification(String text, int duration) {
        if (mRootLayout != null)
            Snackbar.make(mRootLayout, text, duration).show();
    }

    @Override
    public void normalizeToolbar() {
        if (mAppbar != null && mRootLayout != null) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppbar.getLayoutParams();
            AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
            if (behavior != null) {
                int[] consumed = new int[2];
                behavior.onNestedPreScroll(mRootLayout, mAppbar, null, 0, -1000, consumed);
                behavior.onNestedFling(mRootLayout, mAppbar, null, 0, -1000, true);
            }
        }
    }

    @Override
    public void onConnectionChange(boolean isConnected) {
        if (!isConnected)
            showSnackbarNotification(getString(R.string.notification_no_connection), Snackbar.LENGTH_SHORT);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager() != null && getSupportFragmentManager().findFragmentByTag(ChangeFragmentHelper.MAIN_FRAGMENT) == null)
            ChangeFragmentHelper.setMainFragment(this, R.id.main_fragment);
        else
            super.onBackPressed();
    }

    @NonNull
    @Override
    protected PresenterFactory<Main2Presenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public Main2Presenter getParentPresenter() {
        return mPresenter;
    }
}
