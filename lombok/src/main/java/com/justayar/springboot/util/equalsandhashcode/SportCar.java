package com.justayar.springboot.util.equalsandhashcode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true,onlyExplicitlyIncluded = true)
public class SportCar extends Car{

    @EqualsAndHashCode.Include
    private int maxSpeed;
    private double oneHundredKilometersReachTimeInSeconds;

}
