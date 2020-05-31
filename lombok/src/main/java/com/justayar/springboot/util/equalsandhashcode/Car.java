package com.justayar.springboot.util.equalsandhashcode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {

    @EqualsAndHashCode.Include
    private int year;
    @EqualsAndHashCode.Include
    private double horsePower;
    private FuelType fuelType;
    @EqualsAndHashCode.Include
    private String model;

    private static final double GRAVITY = 9.80665;


    public enum FuelType {
        Gasoline,
        Diesel,
        Cng,
        Ethanol,
        BioDiesel;

    }
}
