package com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls;

import com.example.foodrecipemobileapp.Models.Responses.RandomRecipeApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallRandomRecipes {
    @GET("recipes/random")
    Call<RandomRecipeApiResponse> callRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("tags") List<String> tags,
            @Query("number") String number
    );
}
