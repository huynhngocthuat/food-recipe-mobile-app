package com.example.foodrecipemobileapp.Datas.Locals;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.*;
import com.example.foodrecipemobileapp.Utils.Converters;

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
        version = 6)
@TypeConverters({Converters.class})
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    private static RecipeDatabase instance = null;

    public static RecipeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room
                    .databaseBuilder(context, RecipeDatabase.class, "recipeapp")
                    .fallbackToDestructiveMigration()
//                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("DATABASE", "Database created");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d("DATABASE", "Database opened");
            Completable.fromAction(() -> instance.recipeDao().deleteAll())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }
    };
}
