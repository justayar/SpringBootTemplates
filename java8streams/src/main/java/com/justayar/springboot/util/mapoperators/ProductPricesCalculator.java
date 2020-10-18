package com.justayar.springboot.util.mapoperators;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPricesCalculator {

    private static final double TAX_RATE = 1.18;

    public void getPricesWithTaxesByForLoop(List<Double> prices){

        long startTime = System.currentTimeMillis();

        for(int i=0;i<prices.size();i++){
            prices.set(i,prices.get(i)*TAX_RATE);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With For Loop): "+(endTime-startTime)+" miliseconds\n");
    }


    public void getPricesWithTaxesByStreamMap(List<Double> prices){

        long startTime = System.currentTimeMillis();

        prices.stream().map(price -> price * TAX_RATE).collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Stream Map): "+(endTime-startTime)+" miliseconds\n");
    }

    public void mergeAllPricesFromDifferentSourcesWithStreamFlatMap(List<List<Double>> prices){

        long startTime = System.currentTimeMillis();

        List<Double> collect = prices.stream().flatMap(Collection::stream).collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Stream FlatMap): "+(endTime-startTime)+" miliseconds\n");

    }

    public void mergeAllPricesFromDifferentSourcesWithForLoop(List<List<Double>> prices){

        long startTime = System.currentTimeMillis();

        List<Double> mergedPrices = new ArrayList<>();

        for(List<Double> subPrices : prices){

            mergedPrices.addAll(subPrices);
        }

        List<Double> collect = prices.stream().flatMap(Collection::stream).collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With For Loop): "+(endTime-startTime)+" miliseconds\n");

    }



    public List<List<Double>> mockAllPricesFromDifferentSources(){

        List<Double> pricesFromSource1 = List.of(1.0,2.0,3.0);
        List<Double> pricesFromSource2 = List.of(4.0,2.0,5.0);
        List<Double> pricesFromSource3 = List.of(1.0,5.0,6.0);

        return List.of(pricesFromSource1,pricesFromSource2,pricesFromSource3);


    }

    public List<Double> mockProductPrices(){
        List<Double> list = new ArrayList<>();
        for (double i = 1; i < 500000; i++){
            list.add(i);
        }
        return list;
    }
}
