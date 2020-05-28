package com.justayar.springboot.util.tostring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Product {

    private int productId;
    @ToString.Include(name="Product Name",rank = 1)
    private String productName;
    @ToString.Include(name="Product Amount",rank=3)
    private int productAmount;
    @ToString.Include(name="Product Unit Price",rank=2)
    private double productUnitPrice;
    @ToString.Include(name="Product Category",rank=4)
    private ProductSubCategory productCategory;

}
