package com.example.foodrecipemobileapp.Models.Intermediates;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeWithExtendedIngredientsAndInstructions {
    @Embedded
    public Recipe recipe;
    @Relation(
            entity = AnalyzedInstruction.class,
            parentColumn = "idRecipe",
            entityColumn = "idFkRecipe"
    )
    public List<InstructionWithSteps> analyzedInstructions;
    @Relation(
            entity = ExtendedIngredient.class,
            parentColumn = "idRecipe",
            entityColumn = "idFkRecipe"
    )
    public List<ExtendedIngredientAndMeasures> extendedIngredients;
}
