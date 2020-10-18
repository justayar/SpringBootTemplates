package com.justayar.springboot.util.filteroperator;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class TextSearch {

    public void searchTextWithClassicForLoop(List<String> texts, String textToSearched){

        long startTime = System.currentTimeMillis();

        String matchedTexts = "";

        for(String text : texts){
            if(text.contains(textToSearched)) {
                matchedTexts +=", "+text;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Classic For Loop): "+(endTime-startTime)+" miliseconds\n");

    }

    public void searchTextWithStreamFilter(List<String> texts, String textToSearched){

        long startTime = System.currentTimeMillis();

        texts.stream().filter(text -> text.contains(textToSearched)).collect(Collectors.joining(", "));

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal time(With Stream Filter): "+(endTime-startTime)+" miliseconds\n");

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
