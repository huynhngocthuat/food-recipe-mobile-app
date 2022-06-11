package com.example.foodrecipemobileapp.Datas.Remotes;

import android.content.Context;

import com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls.CallInstructions;
import com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls.CallRandomRecipes;
import com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls.CallRecipeDetails;
import com.example.foodrecipemobileapp.Datas.Remotes.ApiCalls.CallSimilarRecipes;
import com.example.foodrecipemobileapp.Listeners.InstructionsListener;
import com.example.foodrecipemobileapp.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipemobileapp.Listeners.RecipeDetailsListener;
import com.example.foodrecipemobileapp.Listeners.SimilarRecipesListener;
import com.example.foodrecipemobileapp.Models.Responses.InstructionsResponse;
import com.example.foodrecipemobileapp.Models.Responses.RandomRecipeApiResponse;
import com.example.foodrecipemobileapp.Models.Responses.RecipeDetailsResponse;
import com.example.foodrecipemobileapp.Models.Responses.SimilarRecipeResponse;
import com.example.foodrecipemobileapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private Context context;
    private String API_KEY;
    private static final String BASE_URL = "https://api.spoonacular.com/";

    // set up retrofit
    private Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

    public RequestManager(Context context){
        this.context = context;
        this.API_KEY = context.getString(R.string.api_key);
    }

    // Call and get random recipes
    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        // Create api call for random recipes
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(API_KEY, tags, "2");

        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    // Call and get random recipe details based on id
    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        // Create api call for recipe details
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.
                callRecipeDetails(
                        id,
                        API_KEY);

        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }

                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    // Call and get random similar recipes based on id
    public void getSimilarRecipes(SimilarRecipesListener listener, int id){
        // Create api call for similar recipes
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecipes.callSimilarRecipe(id, "4", API_KEY);

        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    // Call and get random recipe's instruction based on id
    public void getInstructions(InstructionsListener listener, int id){
        // Create api call for recipe's instruction
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id, API_KEY);

        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
}












