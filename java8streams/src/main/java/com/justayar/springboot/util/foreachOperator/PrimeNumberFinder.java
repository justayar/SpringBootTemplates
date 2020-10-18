package com.justayar.springboot.util.foreachOperator;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberFinder {

    public void withParallelStreamForEach(List numberList){

        long startTime = System.currentTimeMillis();
        printPrimeNumbersText();

        numberList.parallelStream().forEach(number -> {
            if(isPrime(number)){
                System.out.print(number+"/");
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Parallel Stream For Each): "+(endTime-startTime)+" miliseconds\n");
    }

    public void withStreamForEach(List numberList){

        long startTime = System.currentTimeMillis();
        printPrimeNumbersText();

        numberList.stream().forEach(number -> {
            if(isPrime(number)){
                System.out.print(number+"/");
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Stream For Each): "+(endTime-startTime)+" miliseconds\n");
    }

    public void withIterableForEach(List numberList){

        long startTime = System.currentTimeMillis();
        printPrimeNumbersText();

        numberList.forEach(number -> {
            if(isPrime(number)){
                System.out.print(number+"/");
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Iterable For Each): "+(endTime-startTime)+" miliseconds\n");
    }

    public void withClassicForLoop(List numberList){

        long startTime = System.currentTimeMillis();
        printPrimeNumbersText();

        for(int i=0;i<numberList.size();i++){
            if(isPrime(numberList.get(i))){
                System.out.print(numberList.get(i)+"/");

            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Classic For Loop): "+(endTime-startTime)+" miliseconds\n");
    }

    public void withEnhancedForLoop(List numberList){

        long startTime = System.currentTimeMillis();
        printPrimeNumbersText();

        for(Object number: numberList){
            if(isPrime(number)){
                System.out.print(number+"/");

            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Enhanced For Loop): "+(endTime-startTime)+" miliseconds\n");
    }

    private static void printPrimeNumbersText(){
        System.out.print("Prime Numbers:");
    }


    public List<Integer> createMockList(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 100000; i++){
                list.add(i);
        }
        return list;
    }

    private static boolean isPrime(Object number)
    {
        int num = Integer.parseInt(number.toString());
        if (num <= 1)
            return false;
        for (int i = 2; i < num; i++)
            if (num % i == 0)
                return false;
        return true;
    }
}
