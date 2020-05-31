package com.justayar.springboot.util.equalsandhashcode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper= true,onlyExplicitlyIncluded = true)
public class ClassicCar extends Car {

     private boolean hasBenchSeat;
     @EqualsAndHashCode.Include
     private int generationNumber;
}
