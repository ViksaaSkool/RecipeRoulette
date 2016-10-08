package com.recipe.roulette.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.adapters.tabs.RecipeSwipeViewPagerAdapter;
import com.recipe.roulette.app.api.Food2ForkApi;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerRecipeSwipeViewComponent;
import com.recipe.roulette.app.injection.module.RecipeSwipeViewModule;
import com.recipe.roulette.app.presenter.RecipeSwipePresenter;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.view.RecipeSwipeView;
import com.recipe.roulette.app.view.activity.MainActivity;
import com.recipe.roulette.app.view.impl.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by varsovski on 05-Oct-16.
 */
public class RecipeSwipeFragment extends BaseFragment<RecipeSwipePresenter, RecipeSwipeView> implements RecipeSwipeView {

    @Inject
    PresenterFactory<RecipeSwipePresenter> mPresenterFactory;

    @Inject
    Food2ForkApi mFood2ForkApi;

    @BindView(R.id.recipes_viewpager)
    ViewPager mRecipesViewPager;

    public RecipeSwipeFragment() {
        // Required empty public constructor
    }

    public static RecipeSwipeFragment newInstance() {
        return new RecipeSwipeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipeview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUI();
    }


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerRecipeSwipeViewComponent.builder()
                .appComponent(parentComponent)
                .recipeSwipeViewModule(new RecipeSwipeViewModule())
                .build()
                .inject(this);
    }


    @NonNull
    @Override
    protected PresenterFactory<RecipeSwipePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    private void setUI() {
        if (mFood2ForkApi.getSearchResults() != null
                && mFood2ForkApi.getSearchResults().getCount() != null
                && mRecipesViewPager != null) {

            int count = mFood2ForkApi.getSearchResults().getCount();
            mRecipesViewPager.setAdapter(new RecipeSwipeViewPagerAdapter(getFragmentManager(), count));

            ((MainActivity) getActivity()).setToolbar(String.format(getString(R.string.title_search_results), count));
            ((MainActivity) getActivity()).setBackButton(true);
            ((MainActivity)getActivity()).normalizeToolbar();
        }

    }
}
