package com.justayar.springboot.util.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sauce {

    private SauceType sauceType;
    private double amount;


    public enum SauceType{
        Ketchup,
        Mayonnaise,
        Barbecue,
        HoneyMustard,
        Mustard;

    }
}
