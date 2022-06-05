package com.example.foodrecipemobileapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "recipe")
public class Recipe {
    @PrimaryKey
    public int id;
    @ColumnInfo()
    public String title;

    @ColumnInfo()
    public int readyInMinutes;
    public int preparationMinutes;
    public int cookingMinutes;
    public int servings;
    public double pricePerServing;

    @ColumnInfo()
    public int aggregateLikes;
    @ColumnInfo()
    public String image;


    public ArrayList<ExtendedIngredient> extendedIngredients;

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
    public ArrayList<Object> cuisines;
    public ArrayList<String> dishTypes;
    public ArrayList<Object> diets;
    public ArrayList<Object> occasions;
    public String instructions;
    public ArrayList<AnalyzedInstruction> analyzedInstructions;
    public Object originalId;
    public String spoonacularSourceUrl;
}
