package com.justayar.springboot.util.limitandsortedoperators;

import java.util.*;
import java.util.stream.IntStream;

public class RandomNumberGenerator {

    public void createNRandomNumbersWithStreamLimit(int numberOfRandomNumbers){

        long startTime = System.currentTimeMillis();

        Random random = new Random();

        IntStream randomNumberList = random.ints().limit(numberOfRandomNumbers);

        randomNumberList.sorted().forEach(System.out::print);

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Stream Limit): "+(endTime-startTime)+" miliseconds\n");

    }

    public void createNRandomNumbersWithClassicForLoop(int numberOfRandomNumbers){

        long startTime = System.currentTimeMillis();

        Random random = new Random();

        List<Integer> randomNumberList = new ArrayList<>();

        for(int i=0;i<numberOfRandomNumbers;i++){
            int randomNumber = random.nextInt();
            randomNumberList.add(randomNumber);
            System.out.print(randomNumber);
        }

        Collections.sort(randomNumberList);

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With For Loop): "+(endTime-startTime)+" miliseconds\n");

    }
}
