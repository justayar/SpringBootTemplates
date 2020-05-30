package com.justayar.springboot.util.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vegetable {

    private String name;
    private int amount;
}
