package com.example.cargologger.models;

import com.example.cargologger.models.enums.ConditionStatus;

public class Trailer {
    private int id;
    private String name;
    private double width;
    private double length;
    private double height;
    private ConditionStatus conditionStatus;
    private double loadCapacity;
    private Cargo cargo;
}
