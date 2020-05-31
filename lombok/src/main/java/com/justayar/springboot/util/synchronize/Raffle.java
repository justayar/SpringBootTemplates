package com.justayar.springboot.util.synchronize;

import lombok.Data;
import lombok.Synchronized;
import java.util.Arrays;

@Data
public class Raffle {

    private final Object raffleLock = new Object();

    private int currentCounter = 0;

    private static final String [] winnerCounters = {"10","100","1000","10000"};

    @Synchronized("raffleLock")
    public boolean didIWin(){

        setCurrentCounter(getCurrentCounter()+1);

        String predicateCounter = currentCounter+"";

        return Arrays.stream(winnerCounters).anyMatch(predicateCounter::equals);

    }
}
