package com.justayar.springboot;

import com.justayar.springboot.util.CleanUp.CleanUpFeatureExample;
import com.justayar.springboot.util.NonNull.NonNullFeatureExample;
import com.justayar.springboot.util.ValVar.ValVarFeatureExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static java.lang.System.out;

@SpringBootApplication
public class LombokApplication {

	public static void main(String[] args) {

		SpringApplication.run(LombokApplication.class, args);

		out.println("\n---( Lombok Val-Var Example )---\n");

		new ValVarFeatureExample().returnToDoListWithPrioritizedOrder();

		out.println("\n---( Lombok NonNull Example )---\n");


		try{
			new NonNullFeatureExample().isPalindrome(null);

		}catch(IllegalArgumentException ex){
			out.println("Text which will be checked whether it is palindrome or not cannot be null");
			out.println("Error message:"+ex.getMessage());
		}

        out.println("\n---( Lombok CleanUp Example )---\n");


        String secretNumberFromFile = new CleanUpFeatureExample().getSecretNumberFromFile();

        out.println("Secret number from file is: "+secretNumberFromFile );

        new CleanUpFeatureExample().setSecretNumberToBackUpFile(secretNumberFromFile);


	}

}
