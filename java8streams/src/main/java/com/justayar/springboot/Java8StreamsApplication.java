package com.justayar.springboot;

import com.justayar.springboot.util.distinctandcountoperators.UniqueTextFinder;
import com.justayar.springboot.util.filteroperator.TextSearch;
import com.justayar.springboot.util.findfirstandorelseoperators.NumberOperations;
import com.justayar.springboot.util.limitandsortedoperators.RandomNumberGenerator;
import com.justayar.springboot.util.mapoperators.ProductPricesCalculator;
import com.justayar.springboot.util.foreachOperator.PrimeNumberFinder;
import com.justayar.springboot.util.matchoperators.Student;
import com.justayar.springboot.util.matchoperators.StudentSearch;
import com.justayar.springboot.util.peekandtoarrayoperators.Employee;
import com.justayar.springboot.util.peekandtoarrayoperators.EmployeeUtils;
import com.justayar.springboot.util.reduceoperator.MathOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Java8StreamsApplication {


	public static void main(String[] args) throws IOException {

		SpringApplication.run(Java8StreamsApplication.class, args);

		System.out.println("ForEach Operator Demo:\n");
		runForEachOperatorDemo();

		System.out.println("----------------------------------");

		System.out.println("Map Operator Demo:\n");
		runMapOperatorDemo();

		System.out.println("----------------------------------");


		System.out.println("Filter Operator Demo:\n");
		runFilterOperatorDemo();

		System.out.println("----------------------------------");


		System.out.println("Limit And Sorted Operators Demo:\n");
		runLimitAndSortedOperatorsDemo();

		System.out.println("----------------------------------");


		System.out.println("Distinct And Count Operators Demo:\n");
		runDistinctAndCountOperatorsDemo();

		System.out.println("----------------------------------");


		System.out.println("Match Operators Demo:\n");
		runMatchOperatorsDemo();

		System.out.println("----------------------------------");

		System.out.println("Reduce Operators Demo:\n");
		runReduceOperatorDemo();

		System.out.println("----------------------------------");

		System.out.println("FindFirst And OrElse Operators Demo:\n");
		runFindFirstOrElseOperatorsDemo();

		System.out.println("----------------------------------");

		System.out.println("Peek And ToArray Operators Demo:\n");
		runPeekAndToArrayOperatorsDemo();
		System.out.println("Peek Operator is mainly used for debugging. It is not preferred to use in the production.");


	}


	private static void runForEachOperatorDemo(){
		PrimeNumberFinder primeNumberFinder = new PrimeNumberFinder();

		List<Integer> mockList = primeNumberFinder.createMockList();

		primeNumberFinder.withClassicForLoop(mockList);
		primeNumberFinder.withEnhancedForLoop(mockList);
		primeNumberFinder.withIterableForEach(mockList);
		primeNumberFinder.withStreamForEach(mockList);
		primeNumberFinder.withParallelStreamForEach(mockList);
		System.out.println("All implementations except parallel stream process data in sequential order, but in parallel stream order is undefined");
	}

	private static void runMapOperatorDemo(){
		ProductPricesCalculator productPricesCalculator = new ProductPricesCalculator();

		List<Double> doubles = productPricesCalculator.mockProductPrices();

		productPricesCalculator.getPricesWithTaxesByForLoop(doubles);
		productPricesCalculator.getPricesWithTaxesByStreamMap(doubles);

		List<List<Double>> priceList = productPricesCalculator.mockAllPricesFromDifferentSources();

		productPricesCalculator.mergeAllPricesFromDifferentSourcesWithStreamFlatMap(priceList);
		productPricesCalculator.mergeAllPricesFromDifferentSourcesWithForLoop(priceList);
	}

	private static void runFilterOperatorDemo() throws IOException {
		TextSearch textSearch = new TextSearch();

		List<String> mockList = textSearch.createMockList();

		textSearch.searchTextWithClassicForLoop(mockList,"se");
		textSearch.searchTextWithStreamFilter(mockList,"se");
	}

	private static void runLimitAndSortedOperatorsDemo(){
		RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

		randomNumberGenerator.createNRandomNumbersWithClassicForLoop(10000);
		randomNumberGenerator.createNRandomNumbersWithStreamLimit(10000);

	}

	private static void runDistinctAndCountOperatorsDemo() throws IOException {
		UniqueTextFinder uniqueTextFinder = new UniqueTextFinder();

		List<String> mockTexts = uniqueTextFinder.createMockList();

		uniqueTextFinder.findNumberOfUniqueTextsWithForLoop(mockTexts);
		uniqueTextFinder.findNumberOfUniqueTextsWithStreamDistinctAndCount(mockTexts);
	}

	private static  void runMatchOperatorsDemo(){
		StudentSearch studentSearch = new StudentSearch();

		List<Student> students = studentSearch.mockStudents();

		studentSearch.checkAllStudentsHasGpaBelowThanTwoWithStream(students);
		studentSearch.checkAllStudentsHasGpaBelowThanTwoWithForLoop(students);

		studentSearch.checkAllStudentsHasGpaOverThanThreeWithStream(students);
		studentSearch.checkAllStudentsHasGpaOverThanThreeWithForLoop(students);

		studentSearch.checkThereIsAnyStudentWhoIsInComputerEngineeringAndGpaIsOverThanThreeWithStream(students);
		studentSearch.checkThereIsAnyStudentWhoIsInComputerEngineeringAndGpaIsOverThanThreeWithSForLoop(students);
	}

	private static void runReduceOperatorDemo(){
		MathOperations mathOperations = new MathOperations();
		mathOperations.sumOfListWithStreamReduce();
		mathOperations.sumOfListWithForLoop();
	}

	private static void runFindFirstOrElseOperatorsDemo(){
		NumberOperations numberOperations = new NumberOperations();
		numberOperations.findTheMaxNumberInTheListWithStream();
	}

	private static void runPeekAndToArrayOperatorsDemo(){
		EmployeeUtils employeeUtils = new EmployeeUtils();
		List<Employee> employeeList = employeeUtils.mockEmployeeList();
		employeeUtils.filterEmployeesAboveThirtyWithStream(employeeList);
	}


}
