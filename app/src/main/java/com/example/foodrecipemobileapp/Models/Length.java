package com.example.foodrecipemobileapp.Models;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {@ForeignKey(entity = Step.class, parentColumns = "idStep", childColumns = "idFkStep", onDelete = CASCADE)},
        tableName="length"
)
public class Length {
    @PrimaryKey(autoGenerate = true)
    public long idLenght;
    //Foreign key
    public long idFkStep;
    public int number;
    public String unit;
}
