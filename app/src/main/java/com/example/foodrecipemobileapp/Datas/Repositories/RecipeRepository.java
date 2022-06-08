package com.example.foodrecipemobileapp.Datas.Repositories;

import android.app.Application;
import android.util.Log;

import com.example.foodrecipemobileapp.Datas.Locals.RecipeDao;
import com.example.foodrecipemobileapp.Datas.Locals.RecipeDatabase;
import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Intermediates.RecipeWithExtendedIngredientsAndInstructions;
import com.example.foodrecipemobileapp.Models.Recipe;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
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

    public void insertRecipe(Recipe recipe){
        long idRecipe = recipe.idRecipe;
        for(ExtendedIngredient ingredient : recipe.extendedIngredients){
            ingredient.idFkRecipe = idRecipe;
        }
        for(AnalyzedInstruction instruction : recipe.analyzedInstructions){
            instruction.idFkRecipe = idRecipe;
        }
        recipeDao.insertRecipe(recipe)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onComplete() {
                        Log.d("INSERT", "RECIPE INSERT COMPLETE!");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ERR_INSERT", "FAIL TO INSERT RECIPE");
                    }
                }
        );

        recipeDao.insertIngredients(recipe.extendedIngredients)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MaybeObserver<List<Long>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<Long> longs) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ERR_INSERT", "FAIL TO INSERT INGREDIENT");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("INSERT", "INGREDIENT INSERT COMPLETE!");
                    }
                });
        recipeDao.insertInstructions(recipe.analyzedInstructions)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MaybeObserver<List<Long>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<Long> longs) {}

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ERR_INSERT", "FAIL TO INSERT INSTRUCTION");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("INSERT", "INSTRUCTION INSERT COMPLETE!");
                    }
                });
    }

    public void insertRecipes(List<Recipe> recipes){
        for(Recipe recipe : recipes){
            insertRecipe(recipe);
        }
    }

//    public void loadRecipes(int amount){
//        recipeDao.getRecipeWithIngredientsAndInstructions()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribeWith()
//    }
}
