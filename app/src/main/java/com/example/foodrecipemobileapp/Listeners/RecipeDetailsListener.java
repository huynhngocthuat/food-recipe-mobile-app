package com.example.foodrecipemobileapp.Listeners;

import com.example.foodrecipemobileapp.Models.ResponseModels.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
