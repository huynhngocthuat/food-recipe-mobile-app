package com.example.foodrecipemobileapp.Models.Intermediates;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Recipe;

import java.util.ArrayList;

public class RecipeWithExtendedIngredientsAndInstructions {
    @Embedded
    public Recipe recipe;
    @Relation(
            entity = Recipe.class,
            parentColumn = "idRecipe",
            entityColumn = "idFkRecipe"
    )
    public ArrayList<AnalyzedInstruction> analyzedInstructions;
    @Relation(
            entity = Recipe.class,
            parentColumn = "idRecipe",
            entityColumn = "idFkRecipe"
    )
    public ArrayList<ExtendedIngredient> extendedIngredients;
}
