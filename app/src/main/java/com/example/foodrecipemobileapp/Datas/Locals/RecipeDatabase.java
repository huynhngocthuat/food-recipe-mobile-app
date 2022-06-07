package com.example.foodrecipemobileapp.Datas.Locals;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Recipe;

@Database(entities = {Recipe.class, Ingredient.class}, version = 1)
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
