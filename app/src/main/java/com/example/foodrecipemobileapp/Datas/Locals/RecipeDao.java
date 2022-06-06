package com.example.foodrecipemobileapp.Datas.Locals;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.Models.Intermediate.RecipeWithIngredients;

import java.util.ArrayList;

@Dao
public interface RecipeDao {

    @Transaction
    @Insert()
    long insertRecipe(Recipe recipe);

    @Insert
    void insertIngredients(ArrayList<Ingredient> ingredients);

    @Transaction
    @Query("SELECT * FROM recipe")
    ArrayList<RecipeWithIngredients> getRecipeWithIngredients();
}
