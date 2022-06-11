package com.example.foodrecipemobileapp.Datas.Locals;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.*;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;

@Database(
        entities = {
                Recipe.class, Ingredient.class,
                AnalyzedInstruction.class, Equipment.class,
                Length.class, Measures.class,
                Metric.class, Step.class,
                Us.class, ExtendedIngredient.class},
        version = 4)
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
