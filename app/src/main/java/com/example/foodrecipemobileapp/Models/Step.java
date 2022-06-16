package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = @ForeignKey(entity = AnalyzedInstruction.class, parentColumns = "idInstruction", childColumns = "idFkInstruction", onDelete = CASCADE),//end foreignKeys
        tableName = "step")
public class Step {
    @PrimaryKey(autoGenerate = true)
    public long idStep;
    // Foreign key
    public long idFkInstruction;
    public int number;
    public String step;
    @Ignore
    public Length length;
    @Ignore
    public List<Ingredient> ingredients;
    @Ignore
    @SerializedName("equipment")
    public List<Equipment> equipments;
}
