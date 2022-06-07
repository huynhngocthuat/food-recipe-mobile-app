package com.example.foodrecipemobileapp.Models.Intermediates;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.Measures;

public class ExtendedIngredientAndMeasures {
    @Embedded
    public ExtendedIngredient extendedIngredient;
    @Relation(
            entity=ExtendedIngredient.class,
            parentColumn="idExtendedIngredient",
            entityColumn="idFkExtendedIngredient"
    )
    public Measures measures;
}
