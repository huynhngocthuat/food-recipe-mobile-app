package com.example.foodrecipemobileapp.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity=ExtendedIngredient.class,
                parentColumns="idExtendedIngredient",
                childColumns="idFkExtendedIngredient")},
        tableName="measures")
public class Measures {
    @PrimaryKey(autoGenerate = true)
    public long idMeasures;
    //Foreign Key
    public long idFkExtendedIngredient;
    @Ignore
    public Us us;
    @Ignore
    public Metric metric;
}
