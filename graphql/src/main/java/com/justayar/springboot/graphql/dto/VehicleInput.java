package com.justayar.springboot.graphql.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleInput {

    private String name;
    private int horsePower;
    private int bodyMass;
    private FuelType fuelType;
    private UUID brandId;
}
