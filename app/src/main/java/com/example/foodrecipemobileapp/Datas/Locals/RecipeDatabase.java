package com.example.foodrecipemobileapp.Datas.Locals;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.*;

@Database(
        entities = {
                Recipe.class, Ingredient.class,
                AnalyzedInstruction.class, Equipment.class,
                Length.class, Measures.class,
                Metric.class, Step.class,
                Us.class, ExtendedIngredient.class},
        version = 3)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    private static RecipeDatabase instance = null;

    public static RecipeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room
                    .databaseBuilder(context, RecipeDatabase.class, "recipeapp")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
