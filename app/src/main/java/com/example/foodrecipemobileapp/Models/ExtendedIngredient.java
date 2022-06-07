package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(
        foreignKeys = {@ForeignKey(entity=Recipe.class, parentColumns="idRecipe", childColumns="idFkRecipe", onDelete=CASCADE)},
        tableName = "extended_ingredient"
)
public class ExtendedIngredient {
    @PrimaryKey
    public long idExtendedIngredient;
    //Foreign key
    public long idFkRecipe;

    public String aisle;
    public String image;
    public String consistency;
    public String name;
    public String nameClean;
    public String original;
    public String originalName;
    public double amount;
    public String unit;
    public ArrayList<String> meta;
    public Measures measures;
}
