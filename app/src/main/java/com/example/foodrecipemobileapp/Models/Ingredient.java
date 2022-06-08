package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = {
        @ForeignKey(entity = Step.class, parentColumns="idStep", childColumns="idFkStep", onDelete=CASCADE)
        },//end foreignKeys
        tableName = "ingredient")
public class Ingredient {
    @PrimaryKey()
    @SerializedName("id")
    public long idIngredient;
    //Foreign key
    public long idFkStep;

    public String name;
    public String localizedName;
    public String image;
}
