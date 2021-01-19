package com.justayar.springboot.service;

import com.justayar.springboot.config.ApplicationProperties;
import com.justayar.springboot.dto.CovidSummaryDTO;
import com.justayar.springboot.exception.CovidServiceNotAccessibleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class CovidApiBatchServiceImpl implements CovidApiBatchService {


    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public CovidSummaryDTO getCovidSummaryData() {
        log.info("[getCovidSummaryData()] Covid summary api will be called.");

        Map<String,String> headers = new HashMap<>();
        headers.put("X-Access-Token",applicationProperties.getCovidApiProvider().getAccessToken());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        URI apiUrl= UriComponentsBuilder.fromUriString(applicationProperties.getCovidApiProvider().getServiceUrl()+"/summary")
                .build()
                .encode()
                .toUri();

        try {
            return restTemplate.exchange(apiUrl, HttpMethod.GET, entity, CovidSummaryDTO.class).getBody();

        } catch(HttpStatusCodeException ex){
            log.info("[getCovidSummaryData()] Covid summary api -- Exception Response Body is {}", ex.getResponseBodyAsString());
            return null;
        } catch(ResourceAccessException ex){
            throw new CovidServiceNotAccessibleException();
        } catch(RestClientException e){
            log.info("[getExchangeRates()] Exchange rates api -- Exception Message is {}", e.getMessage());
            return null;
        }
    }

}
