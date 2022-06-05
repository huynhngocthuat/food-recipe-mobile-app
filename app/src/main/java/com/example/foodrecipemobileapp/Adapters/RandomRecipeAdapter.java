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
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textViewTitle.setText(list.get(position).title);
        holder.textViewTitle.setSelected(true);
        holder.textViewLikes.setText(list.get(position).aggregateLikes+" Likes");
        holder.textViewServings.setText(list.get(position).servings+" Servings");
        holder.textViewTime.setText(list.get(position).readyInMinutes+" Minutes");
        holder.textViewPrice.setText(list.get(position).pricePerServing+ "$");
        Picasso.get().load(list.get(position).image).into(holder.imageViewFood);

        holder.randomListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class RandomRecipeViewHolder extends RecyclerView.ViewHolder{

    CardView randomListContainer;
    TextView textViewTitle, textViewServings, textViewLikes, textViewTime, textViewPrice;
    ImageView imageViewFood;



    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        randomListContainer = itemView.findViewById(R.id.random_list_container);
        textViewTitle = itemView.findViewById(R.id.textView_title);
        textViewServings = itemView.findViewById(R.id.textView_servings);
        textViewLikes = itemView.findViewById(R.id.textView_likes);
        textViewTime = itemView.findViewById(R.id.textView_time);
        imageViewFood = itemView.findViewById(R.id.imageView_food);
        textViewPrice = itemView.findViewById(R.id.textView_price);

    }
}