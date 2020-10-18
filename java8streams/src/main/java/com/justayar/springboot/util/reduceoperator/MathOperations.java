package com.justayar.springboot.util.reduceoperator;

import java.util.stream.IntStream;

public class MathOperations {

    public void sumOfListWithStreamReduce(){

        long startTime = System.currentTimeMillis();

        IntStream numberStream = IntStream.range(1, 10000);
        Integer sum = numberStream.reduce(0, (a, b) -> a + b);


        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Stream Reduce): "+(endTime-startTime)+" miliseconds\n");
    }

    public void sumOfListWithForLoop(){

        long startTime = System.currentTimeMillis();

        int sum = 0;

        for(int i=1;i<10000;i++){
            sum +=i;
        }


        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With For Loop): "+(endTime-startTime)+" miliseconds\n");
    }
}
