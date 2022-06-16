package com.example.foodrecipemobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipemobileapp.Listeners.RecipeClickListener;
import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> recipeList;
    RecipeClickListener clickListener;

    public RandomRecipeAdapter(Context context,
                               List<Recipe> recipeList,
                               RecipeClickListener clickListener) {
        this.context = context;
        this.recipeList = recipeList;
        this.clickListener = clickListener;
    }

    public void setRecipes(List<Recipe> recipes){
        this.recipeList = recipes;
    }


    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textViewTitle.setText(recipeList.get(position).title);
        holder.textViewTitle.setSelected(true);
        holder.textViewLikes.setText(recipeList.get(position).aggregateLikes+" Likes");
        holder.textViewServings.setText(recipeList.get(position).servings+" Servings");
        holder.textViewTime.setText(recipeList.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(recipeList.get(position).image).into(holder.imageViewFood);

        holder.randomListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onRecipeClicked(String.valueOf(recipeList.get(holder.getAdapterPosition()).idRecipe));
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}


class RandomRecipeViewHolder extends RecyclerView.ViewHolder{

    CardView randomListContainer;
    TextView textViewTitle, textViewServings, textViewLikes, textViewTime;
    ImageView imageViewFood;


    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        randomListContainer = itemView.findViewById(R.id.random_list_container);
        textViewTitle = itemView.findViewById(R.id.textView_title);
        textViewServings = itemView.findViewById(R.id.textView_servings);
        textViewLikes = itemView.findViewById(R.id.textView_likes);
        textViewTime = itemView.findViewById(R.id.textView_time);
        imageViewFood = itemView.findViewById(R.id.imageView_food);
    }
}