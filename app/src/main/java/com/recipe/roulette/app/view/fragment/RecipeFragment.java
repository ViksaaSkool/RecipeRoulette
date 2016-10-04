package com.recipe.roulette.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerRecipeViewComponent;
import com.recipe.roulette.app.injection.module.RecipeViewModule;
import com.recipe.roulette.app.presenter.RecipePresenter;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.view.RecipeView;
import com.recipe.roulette.app.view.impl.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by varsovski on 04-Oct-16.
 */

public class RecipeFragment extends BaseFragment<RecipePresenter, RecipeView> implements RecipeView {

    @Inject
    PresenterFactory<RecipePresenter> mPresenterFactory;

    public RecipeFragment() {
        // Required empty public constructor
    }

    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerRecipeViewComponent.builder()
                .appComponent(parentComponent)
                .recipeViewModule(new RecipeViewModule())
                .build()
                .inject(this);
    }


    @NonNull
    @Override
    protected PresenterFactory<RecipePresenter> getPresenterFactory() {
        return null;
    }

}
