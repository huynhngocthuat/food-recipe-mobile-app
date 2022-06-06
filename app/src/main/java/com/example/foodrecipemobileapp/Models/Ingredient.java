package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity=Recipe.class,
        parentColumns="idRecipe",
        childColumns="idFkRecipe",
        onDelete=CASCADE),//end foreignKeys
        tableName = "ingredient")
public class Ingredient {
    @PrimaryKey()
    public long idIngredient;
    // Foreign key
    public long idFkRecipe;
    @ColumnInfo()
    public String name;
    @ColumnInfo()
    public String localizedName;
    @ColumnInfo()
    public String image;
}
