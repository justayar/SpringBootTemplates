package com.justayar.springboot.feignclient.service;

import com.justayar.springboot.feignclient.config.DemoClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "feign-demo-client"/*, configuration = DemoClientConfiguration.class*/)
public interface DemoClient {

    @GetMapping(value="/demo/{id}",consumes = {MediaType.ALL_VALUE})
    ResponseEntity<String> getDemoCall(@PathVariable String id);

    @PostMapping(value="/demo/save",consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> postDemoCall(String id);

    @PutMapping(value="/demo/update",consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> putDemoCall(String id);

    @DeleteMapping(value="/demo/delete",consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> deleteDemoCall(String id);
}
