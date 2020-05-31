package com.justayar.springboot.util.lazygetter;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class PrimeNumber {

    @Getter(lazy = true)
    private final List<Integer> primeNumbers = getAllPrimeNumbers();

    private ArrayList<Integer> getAllPrimeNumbers() {

        ArrayList<Integer> primeNumbers = new ArrayList<>();

        for (int i = 0; i < 500000; i++) {
            if(isPrime(i)){
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; ++i) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
