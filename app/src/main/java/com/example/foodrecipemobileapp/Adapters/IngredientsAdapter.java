package com.example.foodrecipemobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.getTextViewIngredientsName.setText(list.get(position).name);
        holder.getTextViewIngredientsName.setSelected(true);
        holder.textViewIngredientsQuantity.setText(list.get(position).original);
        holder.textViewIngredientsQuantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageViewIngredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder{

    TextView textViewIngredientsQuantity, getTextViewIngredientsName;
    ImageView imageViewIngredients;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewIngredientsQuantity = itemView.findViewById(R.id.textView_ingredients_quantity);
        getTextViewIngredientsName = itemView.findViewById(R.id.textView_ingredients_name);
        imageViewIngredients = itemView.findViewById(R.id.imageView_ingredients);
    }
}
