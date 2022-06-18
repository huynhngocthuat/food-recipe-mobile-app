package com.example.foodrecipemobileapp.Listeners;

import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.Models.Responses.RandomRecipeApiResponse;

public interface RecipeByIdResponseListener {
    void didFetch(Recipe response, String message);
    void didError(String message);
}
