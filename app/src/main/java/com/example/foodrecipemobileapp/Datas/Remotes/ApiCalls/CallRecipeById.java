package com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls;

import com.example.foodrecipemobileapp.Models.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallRecipeById {
    @GET("/recipes/{id}/information")
    Call<Recipe>  callRecipeById(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}
