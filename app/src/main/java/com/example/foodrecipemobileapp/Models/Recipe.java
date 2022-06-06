package com.example.foodrecipemobileapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "recipe")
public class Recipe {
    @PrimaryKey
    public long idRecipe;
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

    @Ignore
    public boolean vegetarian;
    @Ignore
    public boolean vegan;
    @Ignore
    public boolean glutenFree;
    @Ignore
    public boolean dairyFree;
    @Ignore
    public boolean veryHealthy;
    @Ignore
    public boolean cheap;
    @Ignore
    public boolean veryPopular;
    @Ignore
    public boolean sustainable;
    @Ignore
    public boolean lowFodmap;

    @Ignore
    public int weightWatcherSmartPoints;
    @Ignore
    public String gaps;

    @Ignore
    public int healthScore;
    @Ignore
    public String creditsText;
    @Ignore
    public String sourceName;

    @Ignore
    public String sourceUrl;
    @Ignore
    public int openLicense;

    @Ignore
    public String imageType;
    @Ignore
    public String summary;
    @Ignore
    public ArrayList<String> dishTypes;
    @Ignore
    public ArrayList<String> diets;
    @Ignore
    public String instructions;
    @Ignore
    public ArrayList<AnalyzedInstruction> analyzedInstructions;
    @Ignore
    public String spoonacularSourceUrl;
}
