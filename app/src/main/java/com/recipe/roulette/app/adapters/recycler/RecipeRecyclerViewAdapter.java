package com.recipe.roulette.app.adapters.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by varsovski on 31-Aug-16.
 */
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private List<Recipe> mRecipes;

    public RecipeRecyclerViewAdapter(List<Recipe> recipes) {
        this.mRecipes = recipes;
    }

    @Override
    public RecipeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recipe, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerViewAdapter.ViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        if (recipe != null) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return mRecipes == null ? 0 : mRecipes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipeImageView)
        ImageView recipeImageView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;
        @BindView(R.id.sourceTextView)
        TextView sourceTextView;
        @BindView(R.id.root_card_view)
        CardView rootCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
