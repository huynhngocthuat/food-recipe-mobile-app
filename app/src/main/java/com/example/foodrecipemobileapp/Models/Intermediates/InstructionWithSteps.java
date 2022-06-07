package com.example.foodrecipemobileapp.Models.Intermediates;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.Step;

import java.util.ArrayList;

public class InstructionWithSteps {
    @Embedded
    public AnalyzedInstruction instruction;
    @Relation(
            entity = AnalyzedInstruction.class,
            parentColumn = "idInstruction",
            entityColumn = "idFkInstruction"
    )
    public ArrayList<StepWithIngredientsAndEquipment> steps;
}
