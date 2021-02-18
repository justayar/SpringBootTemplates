package com.justayar.springboot.configdemoclient.dto;

import lombok.Data;

@Data
public class Ticker {
    private String base;
    private String target;
    private String price;
    private String volume;
    private String change;
}
