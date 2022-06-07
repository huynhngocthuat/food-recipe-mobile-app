package com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls;

import com.example.foodrecipemobileapp.Models.Responses.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallSimilarRecipes {
    @GET("recipes/{id}/similar")
    Call<List<SimilarRecipeResponse>> callSimilarRecipe(
            @Path("id") int id,
            @Query("number") String number,
            @Query("apiKey") String apiKey
    );
}
