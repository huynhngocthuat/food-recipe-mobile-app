package com.example.foodrecipemobileapp.Datas.Repositories;

import android.app.Application;

import com.example.foodrecipemobileapp.Datas.Locals.RecipeDao;
import com.example.foodrecipemobileapp.Datas.Locals.RecipeDatabase;

public class RecipeRepository {
    public RecipeDao recipeDao;
    public RecipeDatabase recipeDatabase;

    public RecipeRepository(Application application){
        recipeDatabase = RecipeDatabase.getInstance(application);
        recipeDao = recipeDatabase.recipeDao();
    }


}
