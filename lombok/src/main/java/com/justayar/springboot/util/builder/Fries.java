package com.justayar.springboot.util.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fries {

    private FriesType friesType;
    private FriesSize friesSize;


    public enum FriesType{
        STEAK,
        CHIPS,
        SWEETPOTATO,
        CHEESE,
        MATCHSTICK;
    }

    public enum FriesSize{
        SMALL,
        BIGGER;
    }

}
