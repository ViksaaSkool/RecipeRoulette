package com.recipe.roulette.app.adapters.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.helpers.ChangeActivityHelper;
import com.recipe.roulette.app.model.reddit.RedditRecipeItem;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.util.ShareUtil;
import com.recipe.roulette.app.util.UIUtil;
import com.recipe.roulette.app.view.activity.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by varsovski on 31-Aug-16.
 */
public class RedditRecipeRecyclerViewAdapter extends RecyclerView.Adapter<RedditRecipeRecyclerViewAdapter.ViewHolder> {


    private List<RedditRecipeItem> mRecipes;
    private RequestManager mGlide;

    public RedditRecipeRecyclerViewAdapter(List<RedditRecipeItem> recipes) {
        this.mRecipes = recipes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recipe, parent, false);
        mGlide = RecipeRouletteApplication.getAppComponent().glide();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RedditRecipeItem recipe = mRecipes.get(position);
        if (recipe != null) {


            switch (recipe.getType()) {

                case Constants.VIDEO:

                    holder.typeTextView.setText(R.string.text_play_video);
                    mGlide.load(R.drawable.video_file).into(holder.typeImageView);

                    holder.typeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //open video
                            ShareUtil.openLink(recipe.getItemLink());
                        }
                    });
                    break;

                case Constants.GIF:
                    holder.typeTextView.setText(R.string.text_load_gif);
                    mGlide.load(R.drawable.gif_file).into(holder.typeImageView);


                    holder.typeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String url = recipe.getItemLink();
                            Bundle b = new Bundle();
                            b.putString(Constants.GIF_VIDEO_URL_KEY, url);
                            ChangeActivityHelper.changeActivityExtra((AppCompatActivity) holder.typeLayout.getContext(), DetailsActivity.class, b, false);
                        }
                    });
                    break;
            }


            //set image
            if (recipe.getThumbUrl() != null)
                mGlide.load(recipe.getThumbUrl())
                        .crossFade()
                        .bitmapTransform(new BlurTransformation(holder.recipeImageView.getContext(), Constants.BLUR_RATE_LIST))
                        .centerCrop()
                        .into(holder.recipeImageView);
            else
                LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | image url at recipe( " + position + " ) is null");

            //set title
            if (recipe.getTitle() != null)
                holder.titleTextView.setText(recipe.getTitle());
            else
                LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | title at recipe( " + position + " ) is null");

            //set publisher
            if (recipe.getLinkFlairText() != null)
                holder.sourceTextView.setText(recipe.getLinkFlairText());
            else {
                holder.sourceTextView.setText("");
                LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | publisher at recipe( " + position + " ) is null");
            }

            //share recipe
            holder.shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | SHARED sourceURL = " + recipe.getItemLink());
                    ShareUtil.shareRecipe(holder.shareImageView.getContext(), recipe.getItemLink());
                }
            });

            int proportionalHeight = UIUtil.containerHeight((AppCompatActivity) holder.imageContainer.getContext(), 3);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, proportionalHeight);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            holder.imageContainer.setLayoutParams(params);

        } else {
            LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | recipe( " + position + " ) is null");
        }
    }

    @Override
    public int getItemCount() {
        return mRecipes == null ? 0 : mRecipes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipe_imageview)
        ImageView recipeImageView;
        @BindView(R.id.title_text_view)
        TextView titleTextView;
        @BindView(R.id.source_text_view)
        TextView sourceTextView;
        @BindView(R.id.root_card_view)
        CardView rootCardView;
        @BindView(R.id.container_layout)
        RelativeLayout imageContainer;
        @BindView(R.id.share_image_view)
        ImageView shareImageView;
        @BindView(R.id.type_image_view)
        ImageView typeImageView;
        @BindView(R.id.type_text_view)
        TextView typeTextView;
        @BindView(R.id.type_layout)
        LinearLayout typeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
