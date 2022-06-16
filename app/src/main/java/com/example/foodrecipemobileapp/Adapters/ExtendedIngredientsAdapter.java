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
import com.example.foodrecipemobileapp.Models.Intermediates.ExtendedIngredientAndMeasures;
import com.example.foodrecipemobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExtendedIngredientsAdapter extends RecyclerView.Adapter<ExtendedIngredientsViewHolder>{

    Context context;
    List<ExtendedIngredientAndMeasures> list;

    public ExtendedIngredientsAdapter(Context context, List<ExtendedIngredientAndMeasures> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ExtendedIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExtendedIngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ExtendedIngredientsViewHolder holder, int position) {
        holder.getTextViewIngredientsName.setText(list.get(position).extendedIngredient.name);
        holder.getTextViewIngredientsName.setSelected(true);
        holder.textViewIngredientsQuantity.setText(list.get(position).extendedIngredient.original);
        holder.textViewIngredientsQuantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).extendedIngredient.image).into(holder.imageViewIngredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ExtendedIngredientsViewHolder extends RecyclerView.ViewHolder{

    TextView textViewIngredientsQuantity, getTextViewIngredientsName;
    ImageView imageViewIngredients;

    public ExtendedIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewIngredientsQuantity = itemView.findViewById(R.id.textView_ingredients_quantity);
        getTextViewIngredientsName = itemView.findViewById(R.id.textView_ingredients_name);
        imageViewIngredients = itemView.findViewById(R.id.imageView_ingredients);
    }
}
