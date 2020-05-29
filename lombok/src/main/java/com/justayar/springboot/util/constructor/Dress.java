package com.justayar.springboot.util.constructor;

import lombok.*;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")  // create private constructor with static factory with named "of"
@AllArgsConstructor
@ToString
public class Dress {

    @NonNull
    private FabricType fabricType;
    private String color;
    private String productionPlace;
    private double price;
    @NonNull
    private String brand;
    private Size size;

    private static final String DRESS_SIZE_UNIT = "cm";

    public enum FabricType{
        Silk,
        Jersey,
        Crepe,
        Linen,
        Cotton,
        Canvas,
        Woven,
        Twill;
    }

    public enum Size{
        Small,
        Medium,
        Large,
        XLarge
    }
}
