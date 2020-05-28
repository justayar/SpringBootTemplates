package com.justayar.springboot;

import com.justayar.springboot.util.CleanUp.CleanUpFeatureExample;
import com.justayar.springboot.util.GetterSetterExample.StudentObject;
import com.justayar.springboot.util.NonNull.NonNullFeatureExample;
import com.justayar.springboot.util.ToString.Product;
import com.justayar.springboot.util.ToString.ProductCategory;
import com.justayar.springboot.util.ToString.ProductSubCategory;
import com.justayar.springboot.util.ToString.ShoppingCart;
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


		out.println("\n---( Lombok Getter - Setter Example )---\n");


		StudentObject studentObject = new StudentObject();

		studentObject.setSchoolNumber(123); // It can be editable, there is no any access level restriction
		//studentObject.getSchoolNumber();  Getter method restricted with AccessLevel.NONE

		studentObject.setMajor("Computer Engineering"); // It can be editable, there is no any access level restriction
		studentObject.getMajor(); // There is no restriction.

		studentObject.setPersonName("John Doe"); // It can be editable, there is no any access level restriction
		//studentObject.getPersonName();  Getter method restricted with AccessLevel.PRIVATE


		// studentObject.setStudentSecretCode(); it is not permitted outside of class with AccessLevel.PRIVATE
		// studentObject.getStudentSecretCode(); it is not permitted with AccessLevel.NONE

		//studentObject.setAdvisor() it is not permitted outside of class with AccessLevel.NONE
		studentObject.getAdvisor(); // There is no restriction.

		out.println("New Student: "+studentObject.toString());

		out.println("\n---( Lombok ToString Example )---\n");

		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCart.setCartId(12345);


		Product book = new Product();
		book.setProductId(123);
		book.setProductName("Clean Code Book");
		book.setProductAmount(1);
		book.setProductUnitPrice(100.0);


		ProductSubCategory productSubCategory = new ProductSubCategory();
		productSubCategory.setSubCategoryId(12);
		productSubCategory.setSubCategoryName("e-books");
		productSubCategory.setCategoryId(1);
		productSubCategory.setCategoryName("book");

		book.setProductCategory(productSubCategory);

		shoppingCart.addProductToCart(book);


		Product shoe = new Product();
		shoe.setProductId(221);
		shoe.setProductName("Nike Air Max");
		shoe.setProductAmount(1);
		shoe.setProductUnitPrice(450.0);


		productSubCategory = new ProductSubCategory();
		productSubCategory.setSubCategoryId(23);
		productSubCategory.setSubCategoryName("sport");
		productSubCategory.setCategoryId(2);
		productSubCategory.setCategoryName("shoe");

		shoe.setProductCategory(productSubCategory);

		shoppingCart.addProductToCart(shoe);


		out.println(shoppingCart.toString());

	}

}
