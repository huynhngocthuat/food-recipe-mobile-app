package com.example.foodrecipemobileapp.Listeners;

import com.example.foodrecipemobileapp.Models.Responses.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
