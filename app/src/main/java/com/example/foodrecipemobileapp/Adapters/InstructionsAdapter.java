package com.example.foodrecipemobileapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipemobileapp.Models.InstructionsResponse;
import com.example.foodrecipemobileapp.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsViewHolder>{

    Context context;
    List<InstructionsResponse> list;

    public InstructionsAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        holder.textViewInstructionName.setText(list.get(position).name);
        holder.recyclerViewInstructionSteps.setHasFixedSize(true);
        holder.recyclerViewInstructionSteps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        InstructionStepAdapter stepAdapter = new InstructionStepAdapter(context, list.get(position).steps);
        holder.recyclerViewInstructionSteps.setAdapter(stepAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class InstructionsViewHolder extends RecyclerView.ViewHolder{

    TextView textViewInstructionName;
    RecyclerView recyclerViewInstructionSteps;

    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewInstructionName = itemView.findViewById(R.id.textView_instruction_name);
        recyclerViewInstructionSteps = itemView.findViewById(R.id.recycler_instruction_steps);
    }
}
