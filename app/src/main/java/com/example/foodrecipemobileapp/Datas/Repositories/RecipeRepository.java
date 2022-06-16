package com.example.foodrecipemobileapp.Datas.Repositories;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.foodrecipemobileapp.Datas.Locals.RecipeDao;
import com.example.foodrecipemobileapp.Datas.Locals.RecipeDatabase;
import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.Equipment;
import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Intermediates.ExtendedIngredientAndMeasures;
import com.example.foodrecipemobileapp.Models.Intermediates.RecipeWithExtendedIngredientsAndInstructions;
import com.example.foodrecipemobileapp.Models.Measures;
import com.example.foodrecipemobileapp.Models.Metric;
import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.Models.Step;
import com.example.foodrecipemobileapp.Models.Us;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeRepository {
    private RecipeDao recipeDao;
    private RecipeDatabase recipeDatabase;
    private static RecipeRepository instance;

    public static RecipeRepository getInstance(Application application){
        if(instance == null){
            instance = new RecipeRepository(application);
        }
        return instance;
    }

    private RecipeRepository(Application application){
        recipeDatabase = RecipeDatabase.getInstance(application);
        recipeDao = recipeDatabase.recipeDao();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void populateDatas(List<Recipe> recipes){
        Completable.fromAction(() -> recipeDao.deleteAndInsert(recipes))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertRecipes(List<Recipe> recipes){
        Completable.fromAction(() -> recipeDao.insertAll(recipes))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public Maybe<List<RecipeWithExtendedIngredientsAndInstructions>> getRandomRecipe(int amount){
        return recipeDao.getRecipes(amount);
    }

    public Maybe<RecipeWithExtendedIngredientsAndInstructions> getRecipeById(long id){
        return recipeDao.getRecipeById(id);
    }

    public Maybe<List<RecipeWithExtendedIngredientsAndInstructions>> getRecipesByTag(String tag, int amount){
        return recipeDao.getRecipesByTag(tag, amount);
    }

    public void deteleAll(){
        recipeDao.deleteAll();
    }
}
