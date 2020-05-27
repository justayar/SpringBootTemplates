package com.justayar.springboot;

import com.justayar.springboot.util.ValVarFeatureExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static java.lang.System.out;

@SpringBootApplication
public class LombokApplication {

	public static void main(String[] args) {

		SpringApplication.run(LombokApplication.class, args);

		out.println("---( Lombok Val-Var Example )---");

		new ValVarFeatureExample().returnToDoListWithPrioritizedOrder();

	}

}
