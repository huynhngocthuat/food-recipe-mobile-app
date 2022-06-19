package com.example.foodrecipemobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipemobileapp.Models.Intermediates.StepAndLengthWithIngredientsAndEquipment;
import com.example.foodrecipemobileapp.Models.Step;
import com.example.foodrecipemobileapp.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{

    Context context;
    List<StepAndLengthWithIngredientsAndEquipment> list;

    public InstructionStepAdapter(Context context, List<StepAndLengthWithIngredientsAndEquipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_steps, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.textViewInstructionStepNumber.setText(String.valueOf(list.get(position).step.number));
        holder.textViewInstructionStepTitle.setText(list.get(position).step.step);

        holder.recyclerViewInstructionsIngredients.setHasFixedSize(true);
        holder.recyclerViewInstructionsIngredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionsIngredientsAdapter instructionsIngredientsAdapter = new InstructionsIngredientsAdapter(context, list.get(position).ingredients);
        holder.recyclerViewInstructionsIngredients.setAdapter(instructionsIngredientsAdapter);

        holder.recyclerViewInstructionsEquipments.setHasFixedSize(true);
        holder.recyclerViewInstructionsEquipments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionsEquipmentsAdapter instructionsEquipmentsAdapter = new InstructionsEquipmentsAdapter(context, list.get(position).equipments);
        holder.recyclerViewInstructionsEquipments.setAdapter(instructionsEquipmentsAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder{

    TextView textViewInstructionStepNumber, textViewInstructionStepTitle;
    RecyclerView recyclerViewInstructionsEquipments, recyclerViewInstructionsIngredients;

    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewInstructionStepNumber = itemView.findViewById(R.id.textView_instructions_step_number);
        textViewInstructionStepTitle = itemView.findViewById(R.id.textView_instructions_step_title);
        recyclerViewInstructionsEquipments = itemView.findViewById(R.id.recycler_instructions_equipments);
        recyclerViewInstructionsIngredients = itemView.findViewById(R.id.recycler_instructions_ingredients);
    }
}
