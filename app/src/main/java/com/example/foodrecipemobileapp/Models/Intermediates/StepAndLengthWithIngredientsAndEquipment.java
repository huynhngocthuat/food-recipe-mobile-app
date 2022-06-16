package com.example.foodrecipemobileapp.Models.Intermediates;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.Equipment;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Length;
import com.example.foodrecipemobileapp.Models.Step;

import java.util.ArrayList;
import java.util.List;

public class StepAndLengthWithIngredientsAndEquipment {
    @Embedded
    public Step step;
    @Relation(
            entity = Ingredient.class,
            parentColumn = "idStep",
            entityColumn = "idFkStep"
    )
    public List<Ingredient> ingredients;
    @Relation(
            entity = Equipment.class,
            parentColumn = "idStep",
            entityColumn = "idFkStep"
    )
    public List<Equipment> equipments;
    @Relation(
            entity = Length.class,
            parentColumn = "idStep",
            entityColumn = "idFkStep"
    )
    public Length length;
}
