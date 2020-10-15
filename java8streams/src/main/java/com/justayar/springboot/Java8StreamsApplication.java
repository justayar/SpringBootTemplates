package com.justayar.springboot;

import com.justayar.springboot.util.foreachoperator.PrimeNumberFinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Java8StreamsApplication {

	public static void main(String[] args) {

		SpringApplication.run(Java8StreamsApplication.class, args);

		System.out.println("ForEach Operator Demo:\n");
		runForEachOperatorDemo();

	}


	public static void runForEachOperatorDemo(){
		PrimeNumberFinder primeNumberFinder = new PrimeNumberFinder();
		List<Integer> mockList = primeNumberFinder.createMockList();
		primeNumberFinder.withClassicForLoop(mockList);
		primeNumberFinder.withEnhancedForLoop(mockList);
		primeNumberFinder.withIterableForEach(mockList);
		primeNumberFinder.withStreamForEach(mockList);
		primeNumberFinder.withParallelStreamForEach(mockList);
		System.out.println("All implementations except parallel stream process data in sequential order, but in parallel stream order is undefined");
	}

}
