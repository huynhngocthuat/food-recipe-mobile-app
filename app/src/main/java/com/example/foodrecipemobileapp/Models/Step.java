package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(
        entity = AnalyzedInstruction.class,
        parentColumns = "idInstruction",
        childColumns = "idFkInstruction",
        onDelete = CASCADE),//end foreignKeys
        tableName = "step"
)
public class Step {
    @PrimaryKey(autoGenerate = true)
    public long idStep;
    // Foreign key
    public long idFkInstruction;
    public int number;
    public String step;
    public Length length;
}
