package com.justayar.springboot.configdemoclient.service.impl;

import com.justayar.springboot.configdemoclient.dto.Ticker;
import com.justayar.springboot.configdemoclient.dto.TickerResponse;
import com.justayar.springboot.configdemoclient.service.TickerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RefreshScope
public class CryptonatorTickerService implements TickerService {

    @Value("${market.url}")
    private String marketUrl;

    @Value("${market.base}")
    private String base;

    @Value("${market.target}")
    private String target;

    @Override
    public Ticker getTickerPrices() {
        var restTemplate = new RestTemplate();
        ResponseEntity<TickerResponse> tickerResponse = restTemplate.getForEntity(marketUrl + base + '-' + target, TickerResponse.class);
        return Objects.requireNonNull(tickerResponse.getBody()).getTicker();
    }
}
