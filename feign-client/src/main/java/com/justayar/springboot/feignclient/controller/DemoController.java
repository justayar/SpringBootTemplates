package com.justayar.springboot.feignclient.controller;


import com.justayar.springboot.feignclient.service.DemoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/demo")
public class DemoController {

    @Autowired
    private DemoClient demoClient;

    @GetMapping(value="/",consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<String> getDemoCall(String id){
        return demoClient.getDemoCall(id);
    }

    @PostMapping(value="/save",consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<String> postDemoCall(String id){
        return demoClient.postDemoCall(id);    }

    @PutMapping(value="/update",consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<String> putDemoCall(String id){
        return demoClient.putDemoCall(id);    }

    @DeleteMapping(value="/delete",consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<String> deleteDemoCall(String id){
        return demoClient.deleteDemoCall(id);
    }

}
