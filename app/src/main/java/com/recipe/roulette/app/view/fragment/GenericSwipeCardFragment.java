package com.recipe.roulette.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.api.RedditApi;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.reddit.RedditRecipeItem;
import com.recipe.roulette.app.util.LogUtil;

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
    @BindView(R.id.title_text_view)
    TextView mTitleTextView;
    @BindView(R.id.source_text_view)
    TextView mSourceTextView;
    @BindView(R.id.share_image_view)
    ImageView mShareImageView;

    @Inject
    RedditApi mRedditApi;
    @Inject
    RequestManager mGlide;
    @BindView(R.id.type_image_view)
    ImageView mTypeImageView;
    @BindView(R.id.type_text_view)
    TextView mTypeTextView;
    @BindView(R.id.type_layout)
    LinearLayout mTypeLayout;

    private RedditRecipeItem mRecipe;

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
        if (mRedditApi.getRecipeItems() != null) {
            int index = getArguments().getInt(Constants.SWIPE_CARD_INDEX_KEY);
            mRecipe = mRedditApi.getRecipeItems().get(index);
        }
    }

    public void setUI() {
        if (mRecipeImageView != null
                && mTitleTextView != null
                && mSourceTextView != null
                && mRecipe != null) {

            //load image
            mGlide.load(mRecipe.getItemLink()).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    mTypeTextView.setText(R.string.text_load_gif);
                    if (e != null)
                        LogUtil.d(Constants.ADPR_TAG, "RequestListener<String, GlideDrawable>() | gif load failed; message = " + e.getMessage());
                    return true;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    mTypeLayout.setVisibility(View.GONE);
                    LogUtil.d(Constants.ADPR_TAG, "RequestListener<String, GlideDrawable>() | gif loaded!");
                    return true;
                }
            }).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(mRecipeImageView);
            //set text
            mTitleTextView.setText(mRecipe.getTitle());
            mSourceTextView.setText(mRecipe.getLinkFlairText());
        }
    }

    @OnClick({R.id.recipe_imageview, R.id.source_text_view, R.id.share_image_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recipe_imageview:
               // ShareUtil.openLink(mRecipe.getF2fUrl());
                break;
            case R.id.source_text_view:
              //  ShareUtil.openLink(mRecipe.getSourceUrl());
                break;
            case R.id.share_image_view:
               // ShareUtil.shareRecipe(getActivity(), mRecipe.getSourceUrl());
                break;
        }
    }
}
