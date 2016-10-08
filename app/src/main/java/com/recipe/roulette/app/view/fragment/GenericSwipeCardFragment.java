package com.recipe.roulette.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.api.Food2ForkApi;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.Recipe;
import com.recipe.roulette.app.util.ShareUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by varsovski on 08-Oct-16.
 */

public class GenericSwipeCardFragment extends Fragment {

    @BindView(R.id.recipe_imageview)
    ImageView mRecipeImageView;
    @BindView(R.id.title_textview)
    TextView mTitleTextView;
    @BindView(R.id.source_textview)
    TextView mSourceTextView;
    private Recipe mRecipe;

    @Inject
    Food2ForkApi mFood2ForkApi;

    @Inject
    RequestManager mGlide;


    public static GenericSwipeCardFragment newInstance(int index) {
        GenericSwipeCardFragment fragment = new GenericSwipeCardFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.SWIPE_CARD_INDEX_KEY, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_swipe_card_recipe, container, false);
        ButterKnife.bind(this, view);
        RecipeRouletteApplication.getAppComponent().inject(this);
        setRecipe();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUI();
    }

    private void setRecipe() {
        if (mFood2ForkApi.getSearchResults() != null
                && mFood2ForkApi.getSearchResults().getRecipes() != null) {
            int index = getArguments().getInt(Constants.SWIPE_CARD_INDEX_KEY);
            mRecipe = mFood2ForkApi.getSearchResults().getRecipes().get(index);
        }
    }

    public void setUI() {
        if (mRecipeImageView != null
                && mTitleTextView != null
                && mSourceTextView != null
                && mRecipe != null) {

            //load image
            mGlide.load(mRecipe.getImageUrl()).centerCrop().crossFade().into(mRecipeImageView);

            //set text
            mTitleTextView.setText(mRecipe.getTitle());
            mSourceTextView.setText(mRecipe.getPublisher());
        }
    }

    @OnClick({R.id.recipe_imageview, R.id.source_textview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recipe_imageview:
                ShareUtil.openLink(mRecipe.getF2fUrl());
                break;
            case R.id.source_textview:
                ShareUtil.openLink(mRecipe.getSourceUrl());
                break;
        }
    }
}
