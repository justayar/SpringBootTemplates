package com.justayar.springboot.util.data;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class Ingredient {

    @NonNull
    private String name;
    @NonNull
    private double amount;
    private String brand;
}
