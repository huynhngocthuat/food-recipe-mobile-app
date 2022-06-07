package com.example.foodrecipemobileapp.Datas.Locals;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Intermediates.RecipeWithExtendedIngredientsAndInstructions;
import com.example.foodrecipemobileapp.Models.Recipe;

import java.util.ArrayList;

@Dao
public interface RecipeDao {

    @Transaction
    @Insert()
    long insertRecipe(Recipe recipe);

    @Insert
    void insertIngredients(ArrayList<Ingredient> ingredients);

    @Insert
    void insertInstructions(ArrayList<AnalyzedInstruction> instructions);

    @Transaction
    @Query("SELECT * FROM recipe")
    ArrayList<RecipeWithExtendedIngredientsAndInstructions> getRecipeWithIngredientsAndInstructions();
}
