package com.example.foodrecipemobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientsViewHolder>{

    Context context;
    List<Ingredient> list;

    public InstructionsIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsViewHolder holder, int position) {
        holder.textViewInstructionStepItem.setText(list.get(position).name);
        holder.textViewInstructionStepItem.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageViewInstructionStepItem);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class InstructionIngredientsViewHolder extends RecyclerView.ViewHolder{

    TextView textViewInstructionStepItem;
    ImageView imageViewInstructionStepItem;

    public InstructionIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewInstructionStepItem = itemView.findViewById(R.id.textView_instructions_step_item);
        imageViewInstructionStepItem = itemView.findViewById(R.id.imageView_instructions_step_items);
    }
}
