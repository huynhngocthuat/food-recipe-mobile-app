package com.example.foodrecipemobileapp.Models.Responses;

import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.WinePairing;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsResponse {
    public int id;
    public String title;
    public String image;
    public String imageType;
    public int servings;
    public int readyInMinutes;
    public String license;
    public String sourceName;
    public String sourceUrl;
    public String spoonacularSourceUrl;
    public int aggregateLikes;
    public double healthScore;
    public double spoonacularScore;
    public double pricePerServing;
    public List<Object> analyzedInstructions;
    public boolean cheap;
    public String creditsText;
    public List<Object> cuisines;
    public boolean dairyFree;
    public List<Object> diets;
    public String gaps;
    public boolean glutenFree;
    public String instructions;
    public boolean ketogenic;
    public boolean lowFodmap;
    public boolean sustainable;
    public boolean vegan;
    public boolean vegetarian;
    public boolean veryHealthy;
    public boolean veryPopular;
    public boolean whole30;
    public int weightWatcherSmartPoints;
    public List<String> dishTypes;
    public List<ExtendedIngredient> extendedIngredients;
    public String summary;
    public WinePairing winePairing;
}
