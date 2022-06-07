package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(
        entity = Recipe.class,
        parentColumns = "idRecipe",
        childColumns = "idFkRecipe",
        onDelete = CASCADE),// end foreignKeys
        tableName = "instruction"
)
public class AnalyzedInstruction {
    @PrimaryKey(autoGenerate = true)
    public long idInstruction;
    // Foreign key
    public long idFkRecipe;


    public String name;
    public ArrayList<Step> steps;
}
