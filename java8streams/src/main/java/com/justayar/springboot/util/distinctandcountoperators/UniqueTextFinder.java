package com.justayar.springboot.util.distinctandcountoperators;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UniqueTextFinder {


    public void findNumberOfUniqueTextsWithStreamDistinctAndCount(List<String> texts){

        long startTime = System.currentTimeMillis();

        texts.stream().distinct().count();

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Stream Distinct And Count): "+(endTime-startTime)+" miliseconds\n");
    }

    public void findNumberOfUniqueTextsWithForLoop(List<String> texts){

        long startTime = System.currentTimeMillis();

        List<String> uniqueTexts = new ArrayList<>();
        for(String text : texts){
            if(!uniqueTexts.contains(text)){
                uniqueTexts.add(text);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With For Loop): "+(endTime-startTime)+" miliseconds\n");
    }


    public List<String> createMockList() throws IOException {
        File file = new File("java8streams/randomtexts.txt");

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            br.lines().forEach(stringBuilder::append);
        }

        String[] splittedTexts = stringBuilder.toString().split(" ");

        return ArrayUtils.toUnmodifiableList(splittedTexts);

    }
}
