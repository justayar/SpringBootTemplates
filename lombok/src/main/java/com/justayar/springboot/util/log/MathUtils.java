package com.justayar.springboot.util.log;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class MathUtils {


    public double getCeilOfPrice(double price){

        log.info("Price for ceiling operation is "+price);

        return Math.ceil(price);
    }

    public double getFloorOfPrice(double price){

        log.info("Price for floor operation is "+price);

        return Math.floor(price);
    }

}
