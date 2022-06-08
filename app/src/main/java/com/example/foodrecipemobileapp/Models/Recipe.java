package com.example.foodrecipemobileapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipe")
public class Recipe {
    @PrimaryKey
    @SerializedName("id")
    public long idRecipe;
    public String title;

    public int readyInMinutes;
    public int preparationMinutes;
    public int cookingMinutes;
    public int servings;
    public double pricePerServing;

    public int aggregateLikes;
    public String image;

    public boolean vegetarian;
    public boolean vegan;
    public boolean glutenFree;
    public boolean dairyFree;
    public boolean veryHealthy;
    public boolean cheap;
    public boolean veryPopular;
    public boolean sustainable;
    public boolean lowFodmap;

    public int weightWatcherSmartPoints;
    public String gaps;

    public int healthScore;
    public String creditsText;
    public String sourceName;

    public String sourceUrl;
    public int openLicense;

    public String imageType;
    public String summary;
    @Ignore
    public List<String> dishTypes;
    @Ignore
    public List<String> diets;
    public String instructions;
    public String spoonacularSourceUrl;
    @Ignore
    public List<ExtendedIngredient> extendedIngredients;
    @Ignore
    public List<AnalyzedInstruction> analyzedInstructions;
}
