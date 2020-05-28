package com.justayar.springboot.util.ToString;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ShoppingCart {

    @ToString.Exclude
    private int cartId;
    private double cartTotal=0.0;
    private List<Product> productList = new ArrayList<>();

    public void addProductToCart(Product product){
        productList.add(product);
        cartTotal += product.getProductAmount()*product.getProductUnitPrice();
    }


}
