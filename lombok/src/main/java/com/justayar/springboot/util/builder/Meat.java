package com.justayar.springboot.util.builder;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class Meat {

    private MeatType meatType;
    private double weight;
    private CookingRate cookingRate;
    @Singular("sauces")
    private List<Sauce> sauceList;


    public enum MeatType{
        RedMeat,
        Pork,
        Chicken,
        Fish;
    }

    public enum CookingRate{
        Rare,
        MediumRare,
        Medium,
        MediumWell,
        WellDone;
    }


}
