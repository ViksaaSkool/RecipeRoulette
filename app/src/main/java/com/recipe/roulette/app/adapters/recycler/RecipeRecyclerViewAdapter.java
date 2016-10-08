package com.recipe.roulette.app.adapters.recycler;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.recipe.roulette.app.R;
import com.recipe.roulette.app.RecipeRouletteApplication;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.Recipe;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.util.ShareUtil;
import com.recipe.roulette.app.util.UIUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by varsovski on 31-Aug-16.
 */
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private List<Recipe> mRecipes;
    private RequestManager mGlide;

    public RecipeRecyclerViewAdapter(List<Recipe> recipes) {
        this.mRecipes = recipes;
    }

    @Override
    public RecipeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recipe, parent, false);
        mGlide = RecipeRouletteApplication.getAppComponent().glide();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerViewAdapter.ViewHolder holder, int position) {
        final Recipe recipe = mRecipes.get(position);
        if (recipe != null) {

            //set image
            if (recipe.getImageUrl() != null)
                mGlide.load(recipe.getImageUrl()).centerCrop().crossFade().into(holder.recipeImageView);
            else
                LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | image url at recipe( " + position + " ) is null");

            //set title
            if (recipe.getTitle() != null)
                holder.titleTextView.setText(recipe.getTitle());
            else
                LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | title at recipe( " + position + " ) is null");

            //set publisher
            if (recipe.getPublisher() != null)
                holder.sourceTextView.setText(recipe.getPublisher());
            else
                LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | publisher at recipe( " + position + " ) is null");

            //open F2F link
            holder.recipeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | f2fURL = " + recipe.getF2fUrl());
                    ShareUtil.openLink(recipe.getF2fUrl());
                }
            });

            //open source link
            holder.sourceTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d(Constants.ADPR_TAG, "RecipeRecyclerViewAdapter onBindViewHolder() | sourceURL = " + recipe.getSourceUrl());
                    ShareUtil.openLink(recipe.getSourceUrl());
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
        @BindView(R.id.title_textview)
        TextView titleTextView;
        @BindView(R.id.source_textview)
        TextView sourceTextView;
        @BindView(R.id.root_card_view)
        CardView rootCardView;
        @BindView(R.id.container_layout)
        RelativeLayout imageContainer;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
