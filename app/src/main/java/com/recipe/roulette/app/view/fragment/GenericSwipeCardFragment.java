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
import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.api.RedditApi;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.helpers.ChangeActivityHelper;
import com.recipe.roulette.app.model.reddit.RedditRecipeItem;
import com.recipe.roulette.app.util.ShareUtil;
import com.recipe.roulette.app.view.activity.DetailsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

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
    @BindView(R.id.type_image_view)
    ImageView mTypeImageView;
    @BindView(R.id.type_text_view)
    TextView mTypeTextView;
    @BindView(R.id.type_layout)
    LinearLayout mTypeLayout;

    @Inject
    RedditApi mRedditApi;
    @Inject
    RequestManager mGlide;


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
            mGlide.load(mRecipe.getThumbUrl())
                    .crossFade()
                    .bitmapTransform(new BlurTransformation(getActivity(), Constants.BLUR_RATE_SWIPE))
                    .centerCrop()
                    .into(mRecipeImageView);

            if (mRecipe.getType() == Constants.GIF){
                mGlide.load(R.drawable.gif_file).into(mTypeImageView);
                mTypeTextView.setText(R.string.text_load_gif);
            }

            else{
                mGlide.load(R.drawable.video_file).into(mTypeImageView);
                mTypeTextView.setText(R.string.text_play_video);
            }

            //set text
            mTitleTextView.setText(mRecipe.getTitle());
            mSourceTextView.setText(mRecipe.getLinkFlairText());

            //because it looks better
            mTypeLayout.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.recipe_imageview, R.id.source_text_view, R.id.share_image_view, R.id.type_layout})
    public void onClick(View view) {
        String url = mRecipe.getItemLink();
        Bundle b = new Bundle();
        b.putString(Constants.GIF_VIDEO_URL_KEY, url);
        switch (view.getId()) {
            case R.id.recipe_imageview:
                if (mRecipe.getType() == Constants.GIF)
                    ChangeActivityHelper.changeActivityExtra(getActivity(), DetailsActivity.class, b, false);
                else
                    ShareUtil.openLink(url);
                break;
            case R.id.source_text_view:
                ShareUtil.openLink(url);
                break;
            case R.id.share_image_view:
                ShareUtil.shareRecipe(getActivity(), url);
                break;
            case R.id.type_layout:
                if (mRecipe.getType() == Constants.GIF)
                    ChangeActivityHelper.changeActivityExtra(getActivity(), DetailsActivity.class, b, false);
                else
                    ShareUtil.openLink(url);
                break;
        }
    }
}
