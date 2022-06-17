package com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls;

import com.example.foodrecipemobileapp.Models.Responses.RecipeDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallRecipeDetails {
    @GET("recipes/{id}/information")
    Call<RecipeDetailsResponse> callRecipeDetails(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}
