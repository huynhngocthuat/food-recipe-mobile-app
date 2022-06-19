package com.example.foodrecipemobileapp.Models.Intermediates;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.foodrecipemobileapp.Models.Measures;
import com.example.foodrecipemobileapp.Models.Metric;
import com.example.foodrecipemobileapp.Models.Us;

public class USAndMetricMeasures {
    @Embedded
    public Measures measures;
    @Relation(
            entity = Us.class,
            parentColumn="idMeasures",
            entityColumn="idFkMeasures"
    )
    public Us us;
    @Relation(
            entity = Metric.class,
            parentColumn="idMeasures",
            entityColumn="idFkMeasures"
    )
    public Metric metric;
}
