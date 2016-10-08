package com.recipe.roulette.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.adapters.recycler.RecipeRecyclerViewAdapter;
import com.recipe.roulette.app.api.Food2ForkApi;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerRecipeListViewComponent;
import com.recipe.roulette.app.injection.module.RecipeListViewModule;
import com.recipe.roulette.app.presenter.RecipeListPresenter;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.view.RecipeListView;
import com.recipe.roulette.app.view.activity.MainActivity;
import com.recipe.roulette.app.view.impl.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by varsovski on 04-Oct-16.
 */

public class RecipeListFragment extends BaseFragment<RecipeListPresenter, RecipeListView> implements RecipeListView {

    @Inject
    PresenterFactory<RecipeListPresenter> mPresenterFactory;

    @Inject
    Food2ForkApi mFood2ForkApi;

    @BindView(R.id.recipes_recycler_view)
    RecyclerView mRecipesRecyclerView;

    private RecipeRecyclerViewAdapter mRecipeRecyclerViewAdapter;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    public static RecipeListFragment newInstance() {
        return new RecipeListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
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
        DaggerRecipeListViewComponent.builder()
                .appComponent(parentComponent)
                .recipeListViewModule(new RecipeListViewModule())
                .build()
                .inject(this);
    }


    @NonNull
    @Override
    protected PresenterFactory<RecipeListPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    public void setUI() {

        //set the list
        if (mRecipesRecyclerView != null) {
            mRecipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(mFood2ForkApi.getSearchResults().getRecipes());
            mRecipesRecyclerView.setHasFixedSize(true);
            mRecipesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecipesRecyclerView.setItemAnimator(new SlideInUpAnimator());
            mRecipesRecyclerView.setAdapter(mRecipeRecyclerViewAdapter);
        }

        //set the toolbar
        int count = mFood2ForkApi.getSearchResults().getCount();
        ((MainActivity) getActivity()).setToolbar(String.format(getString(R.string.title_search_results), count));

        //set back button
        ((MainActivity) getActivity()).setBackButton(true);
    }
}
