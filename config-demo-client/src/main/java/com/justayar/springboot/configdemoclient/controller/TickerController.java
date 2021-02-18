package com.justayar.springboot.configdemoclient.controller;

import com.justayar.springboot.configdemoclient.dto.Ticker;
import com.justayar.springboot.configdemoclient.service.TickerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticker")
public class TickerController {
    private TickerService tickerService;

    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping
    public Ticker getPrices(){
        return tickerService.getTickerPrices();
    }
}
