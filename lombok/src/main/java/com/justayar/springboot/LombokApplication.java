package com.justayar.springboot;

import com.justayar.springboot.util.builder.*;
import com.justayar.springboot.util.cleanup.CleanUpFeatureExample;
import com.justayar.springboot.util.constructor.Dress;
import com.justayar.springboot.util.data.Ingredient;
import com.justayar.springboot.util.data.Meal;
import com.justayar.springboot.util.equalsandhashcode.Car;
import com.justayar.springboot.util.equalsandhashcode.ClassicCar;
import com.justayar.springboot.util.gettersetter.StudentObject;
import com.justayar.springboot.util.lazygetter.PrimeNumber;
import com.justayar.springboot.util.log.MathUtils;
import com.justayar.springboot.util.log.TextUtils;
import com.justayar.springboot.util.nonnull.NonNullFeatureExample;
import com.justayar.springboot.util.sneakythrows.Reader;
import com.justayar.springboot.util.synchronize.Raffle;
import com.justayar.springboot.util.tostring.Product;
import com.justayar.springboot.util.tostring.ProductSubCategory;
import com.justayar.springboot.util.tostring.ShoppingCart;
import com.justayar.springboot.util.value.Lecture;
import com.justayar.springboot.util.valvar.ValVarFeatureExample;
import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

@SpringBootApplication
public class LombokApplication {

	public static void main(String[] args) {

		SpringApplication.run(LombokApplication.class, args);

		out.println("\n---( Lombok Val-Var Example )---\n");

        runValVarExample();

        out.println("\n---( Lombok NonNull Example )---\n");


        runNonNullExample();


        out.println("\n---( Lombok Cleanup Example )---\n");


        runCleanUpExample();


        out.println("\n---( Lombok Getter - Setter Example )---\n");


        runGetterSetterExample();

        out.println("\n---( Lombok ToString Example )---\n");

        runToStringExample();

        out.println("\n---( Lombok Equals And Hash Code Example )---\n");

        runEqualsAndHashCodeExample();

        out.println("\n---( Lombok NoArgs,RequiredArgs,AllArgs Constructor Example )---\n");

        runNoArgsRequiredArgsAllArgsConstructorExample();

        out.println("\n---( Lombok Data Example )---\n");

        runDataExample();

        out.println("\n---( Lombok Value Example )---\n");

        runValueExample();

        out.println("\n---( Lombok Builder Example )---\n");

        runBuilderExample();

        out.println("\n---( Lombok SneakyThrows Example )---\n");

        runSneakyThrowsExample();

        out.println("\n---( Lombok Synchronized Example )---\n");

        runSynchronizedExample();

        out.println("\n---( Lombok Lazy Getter Example )---\n");

        runLazyGetterExample();

        out.println("\n---( Lombok Log Example )---\n");

        runLogExample();

    }

    private static void runLogExample() {
        TextUtils textUtils = new TextUtils();

        String text = "spring";

        out.println("Text: "+text+" Reversed Text: "+textUtils.reverseOfText(text));

        MathUtils mathUtils = new MathUtils();

        double priceToCeil = 1.23;

        out.println("Price: "+priceToCeil+" Ceiled Price: "+mathUtils.getCeilOfPrice(priceToCeil));

        double priceToFloor = 1.23;

        out.println("Price: "+priceToFloor+" Floored Price: "+mathUtils.getFloorOfPrice(priceToFloor));
    }

    private static void runLazyGetterExample() {
        PrimeNumber prime = new PrimeNumber();

        long startTime = System.currentTimeMillis();

        List<Integer> primeNumbers = prime.getPrimeNumbers();

        long endTime = System.currentTimeMillis();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(endTime -startTime);


        out.println("There are "+primeNumbers.size()+" numbers in "+seconds+" seconds.");

        startTime = System.currentTimeMillis();

        primeNumbers = prime.getPrimeNumbers();

        endTime = System.currentTimeMillis();


        /* prime numbers from cache */

        seconds = TimeUnit.MILLISECONDS.toSeconds(endTime -startTime);

        out.println("There are "+primeNumbers.size()+" numbers in "+seconds+" seconds.");
    }

    private static void runSynchronizedExample() {
        Raffle raffle = new Raffle();
        for(int i=0;i<11;i++){
            out.println("Chance "+(i+1)+" result is "+raffle.didIWin());
        }
    }

    private static void runSneakyThrowsExample() {
        Reader reader = new Reader();

        String result = reader.getSecretNumberFromFile();

        out.println("Result from sneaky throws without any IOException Result is:"+result);
    }

    private static void runBuilderExample() {
        Menu menu = Menu.builder().build();

        Drink drink = Drink.drinkBuilder().drinkSize(Drink.DrinkSize.LARGE).drinkType(Drink.DrinkType.COKE).execute();
        Fries fries = Fries.builder().friesSize(Fries.FriesSize.BIGGER).friesType(Fries.FriesType.SWEETPOTATO).build();

        Bread bread = Bread.builder().breadType(Bread.BreadType.White).isIncludeSesame(false).build();
        Meat meat = Meat.builder()
                .meatType(Meat.MeatType.RedMeat)
                .cookingRate(Meat.CookingRate.Medium)
                .weight(200)
                .sauces(Sauce.builder().sauceType(Sauce.SauceType.Barbecue).amount(1).build())
                .sauces(Sauce.builder().sauceType(Sauce.SauceType.Barbecue).amount(1).build()).build();
        Vegetable lettuce = Vegetable.builder()
                                 .name("lettuce")
                                 .amount(1).build();

        Vegetable onion = Vegetable.builder()
                .name("onion")
                .amount(1).build();

        Sauce ketchup = Sauce.builder()
                                .amount(1)
                                .sauceType(Sauce.SauceType.Ketchup)
                                .build();

        Sauce mayonnaise = Sauce.builder()
                .amount(1)
                .sauceType(Sauce.SauceType.Mayonnaise)
                .build();

        Hamburger hamburger = Hamburger.builder().build();

        hamburger = hamburger.toBuilder()
                .bread(bread)
                .meat(meat)
                .vegetables(lettuce)
                .vegetables(onion)
                .sauces(ketchup)
                .sauces(mayonnaise).build();


        menu = menu.toBuilder()
                .drink(drink)
                .fries(fries)
                .hamburger(hamburger)
                .sauces(mayonnaise).build();


        Order order = Order.builder()
                            .orderId(1)
                            .menu(menu)
                            .build();


        out.println("Order is: "+order.toString());
    }

    private static void runValueExample() {
        Lecture lecture = Lecture.of(123,"Math","Robert Martin","Science",2);

        out.println("Lecture details are: "+lecture.toString());
    }

    private static void runDataExample() {
        var ingredientList = new ArrayList<Ingredient>();

        ingredientList.add(new Ingredient("onion",1));
        ingredientList.add(new Ingredient("tomato",0.5));
        ingredientList.add(new Ingredient("egg",5));


        Meal menemen = Meal.of("menemen","Turkey",ingredientList);

        out.println("Meal is "+menemen.getMealName());
        out.println("Meal ingredients are "+ingredientList.toString());
    }

    private static void runNoArgsRequiredArgsAllArgsConstructorExample() {
        Dress dressWithNoArgs = new Dress();

        Dress dressWithRequiredArgs = Dress.of(Dress.FabricType.Cotton,"Prada");

        Dress dressWithAllArgs = new Dress(Dress.FabricType.Canvas,"RED","Turkey",150,"Koton", Dress.Size.Large);

        out.println("Dress With No Args:"+dressWithNoArgs.toString());

        out.println("Dress With Required Args:"+dressWithRequiredArgs.toString());

        out.println("Dress With All Args:"+dressWithAllArgs.toString());
    }

    private static void runEqualsAndHashCodeExample() {
        Car car1  = new ClassicCar();
        car1.setYear(1961);
        car1.setModel("Jaguar E-Type");
        car1.setFuelType(Car.FuelType.Gasoline);
        ((ClassicCar) car1).setGenerationNumber(1);
        car1.setHorsePower(265);
        ((ClassicCar) car1).setHasBenchSeat(false);

        Car car2  = new ClassicCar();
        car2.setYear(1961);
        car2.setModel("Jaguar E-Type");
        car2.setFuelType(Car.FuelType.Diesel);
        ((ClassicCar) car2).setGenerationNumber(1);
        car2.setHorsePower(265);
        ((ClassicCar) car2).setHasBenchSeat(true);

        Car car3  = new ClassicCar();
        car3.setYear(1972);
        car3.setModel("Jaguar E-Type");
        car3.setFuelType(Car.FuelType.Diesel);
        ((ClassicCar) car3).setGenerationNumber(1);
        car3.setHorsePower(265);
        ((ClassicCar) car3).setHasBenchSeat(true);

        if(car1.equals(car2)){
            out.println("Car 1 and Car 2 are same cars.");

        }else{
            out.println("Car 1 and Car 2 are different cars.");
        }

        if(car1.equals(car3)){
            out.println("Car 1 and Car 3 are same cars.");
        }else{
            out.println("Car 1 and Car 3 are different cars.");
        }

        if(car2.equals(car3)){
            out.println("Car 2 and Car 3 are same cars.");
        }else{
            out.println("Car 2 and Car 3 are different cars.");
        }


        out.println("\nCar 1 Hash Code:"+car1.hashCode());
        out.println("Car 2 Hash Code:"+car2.hashCode());
        out.println("Car 3 Hash Code:"+car3.hashCode());

    }

    private static void runToStringExample() {
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

    private static void runGetterSetterExample() {
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
    }

    private static void runCleanUpExample() {
        String secretNumberFromFile = new CleanUpFeatureExample().getSecretNumberFromFile();

        out.println("Secret number from file is: "+secretNumberFromFile );

        new CleanUpFeatureExample().setSecretNumberToBackUpFile(secretNumberFromFile);
    }

    private static void runNonNullExample() {
        try{
            new NonNullFeatureExample().isPalindrome(null);

        }catch(IllegalArgumentException ex){
            out.println("Text which will be checked whether it is palindrome or not cannot be null");
            out.println("Error message:"+ex.getMessage());
        }
    }

    private static void runValVarExample() {
        new ValVarFeatureExample().returnToDoListWithPrioritizedOrder();
    }

}
