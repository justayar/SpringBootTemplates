package com.justayar.springboot.util.findfirstandorelseoperators;

import java.util.Comparator;
import java.util.stream.Stream;

public class NumberOperations {

    public void findTheMaxNumberInTheListWithStream(){

        long startTime = System.currentTimeMillis();

        Stream numberList = Stream.empty(); //Stream.of(2,50,-3,120,1,0,78,111);

        numberList.sorted(Comparator.reverseOrder()).findFirst().orElse(null);

        long endTime = System.currentTimeMillis();
        System.out.println("\n None Match Total time(With Stream): "+(endTime-startTime)+" miliseconds\n");
    }
}
