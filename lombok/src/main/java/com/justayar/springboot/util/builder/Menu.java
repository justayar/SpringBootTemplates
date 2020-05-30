package com.justayar.springboot.util.builder;

import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Menu {

    private Hamburger hamburger;
    private Drink drink;
    private Fries fries;
    @Singular("sauces")
    private List<Sauce> sauces;


    public void giveOrderToKitchen(){

    }
}
