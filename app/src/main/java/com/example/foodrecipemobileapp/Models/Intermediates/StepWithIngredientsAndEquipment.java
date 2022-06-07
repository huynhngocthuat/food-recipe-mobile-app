package com.example.foodrecipemobileapp.Models.Intermediates;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.Equipment;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Length;
import com.example.foodrecipemobileapp.Models.Step;

import java.util.ArrayList;

public class StepWithIngredientsAndEquipment {
    @Embedded
    public Step step;
    @Relation(
            entity = Step.class,
            parentColumn = "idStep",
            entityColumn = "idFkStep"
    )
    public ArrayList<Ingredient> ingredients;
    @Relation(
            entity = Step.class,
            parentColumn = "idStep",
            entityColumn = "idFkStep"
    )
    public ArrayList<Equipment> equipments;
    @Relation(
            entity = Step.class,
            parentColumn = "idStep",
            entityColumn = "idFkStep"
    )
    public Length length;
}
