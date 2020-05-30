package com.justayar.springboot.util.builder;

import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Hamburger {


    private Bread bread;
    private Meat meat;
    @Singular("vegetables")
    private List<Vegetable> vegetableList;
    @Singular("sauces")
    private List<Sauce> sauces;
}
