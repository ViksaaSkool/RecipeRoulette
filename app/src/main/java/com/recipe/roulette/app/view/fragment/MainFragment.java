package com.recipe.roulette.app.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerCustomViewComponent;
import com.recipe.roulette.app.injection.module.CustomViewModule;
import com.recipe.roulette.app.presenter.CustomPresenter;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.util.ToastUtil;
import com.recipe.roulette.app.view.CustomView;
import com.recipe.roulette.app.view.impl.BaseFragment;

import javax.inject.Inject;

public final class MainFragment extends BaseFragment<CustomPresenter, CustomView> implements CustomView {

    @Inject
    PresenterFactory<CustomPresenter> mPresenterFactory;


    @Inject
    SharedPreferences mSharedPreferences;

    // Your presenter is available using the mPresenter variable

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart

    }

    @Override
    public void onStart() {
        super.onStart();
        ToastUtil.showToast(mSharedPreferences.getString("H", "ERROR"), Toast.LENGTH_SHORT);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCustomViewComponent.builder()
                .appComponent(parentComponent)
                .customViewModule(new CustomViewModule())
                .build()
                .inject(this);

    }

    @NonNull
    @Override
    protected PresenterFactory<CustomPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
