package com.justayar.springboot.util.matchoperators;


import lombok.Builder;
import lombok.Value;
@Builder
@Value
public class Student {

    private int id;
    private String name;
    private String major;
    private double gpa;
}
