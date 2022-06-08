package com.example.foodrecipemobileapp.Datas.Locals;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Intermediates.RecipeWithExtendedIngredientsAndInstructions;
import com.example.foodrecipemobileapp.Models.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface RecipeDao {

    @Transaction
    @Insert()
    Completable insertRecipe(Recipe recipe);

    @Transaction
    @Insert
    Completable insertRecipes(List<Recipe> recipes);

    @Insert
    Maybe<List<Long>> insertIngredients(List<ExtendedIngredient> ingredients);

    @Insert
    Maybe<List<Long>> insertInstructions(List<AnalyzedInstruction> instructions);



    @Transaction
    @Query("SELECT * FROM recipe")
    Maybe<List<RecipeWithExtendedIngredientsAndInstructions>> getRecipeWithIngredientsAndInstructions();
}
