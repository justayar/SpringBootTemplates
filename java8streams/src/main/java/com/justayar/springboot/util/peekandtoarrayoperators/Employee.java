package com.justayar.springboot.util.peekandtoarrayoperators;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Employee {

    private int id;
    private int age;
    private double salary;

}
