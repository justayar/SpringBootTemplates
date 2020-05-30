package com.justayar.springboot.util.builder;

import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
@Builder(toBuilder = true)
public class Order {

    private int orderId;
    @Builder.Default
    private String orderTime = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss").format(Calendar.getInstance().getTime());
    private Menu menu;
}
