package com.justayar.springboot.util.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "YourDrinkBuilder", buildMethodName = "execute", builderMethodName = "drinkBuilder",toBuilder = true)
public class Drink {

    private DrinkType drinkType;
    private DrinkSize drinkSize;

    public enum DrinkType{
        COKE,
        FANTA,
        SPRITE,
        AYRAN;
    }

    public enum DrinkSize{
        SMALL,
        MEDIUM,
        LARGE,
        XLARGE;
    }
}
