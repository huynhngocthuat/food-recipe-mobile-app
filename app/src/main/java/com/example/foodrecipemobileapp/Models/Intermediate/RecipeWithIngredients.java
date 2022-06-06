package com.example.foodrecipemobileapp.Models.Intermediate;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Recipe;

import java.util.ArrayList;

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "idRecipe",
            entityColumn = "idFkRecipe"
    )
    public ArrayList<Ingredient> ingredients;
}
