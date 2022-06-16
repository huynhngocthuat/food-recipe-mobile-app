package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(
        foreignKeys = {@ForeignKey(entity=Recipe.class, parentColumns="idRecipe", childColumns="idFkRecipe", onDelete=CASCADE)},
        tableName = "extended_ingredient"
)
public class ExtendedIngredient {
    @PrimaryKey(autoGenerate = true)
    public long idExtendedIngredient;
    @SerializedName("id")
    public long _idExtendedIngredient;
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
    @Ignore
    public List<String> meta;
    @Ignore
    public Measures measures;
}
