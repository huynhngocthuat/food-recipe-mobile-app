package com.example.foodrecipemobileapp.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(entity=Measures.class,
                        parentColumns="idMeasures",
                        childColumns="idFkMeasures",
                        onDelete=CASCADE)},
        tableName="US")
public class Us {
    @PrimaryKey(autoGenerate = true)
    public long idUSMeasure;
    //Foreign key
    public long idFkMeasures;
    public double amount;
    public String unitShort;
    public String unitLong;
}
