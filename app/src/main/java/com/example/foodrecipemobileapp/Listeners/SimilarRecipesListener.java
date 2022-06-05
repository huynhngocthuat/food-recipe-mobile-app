package com.example.foodrecipemobileapp.Listeners;

import com.example.foodrecipemobileapp.Models.ResponseModels.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> responses, String message);
    void didError(String message);
}
