package com.justayar.springboot.feigndemoclient.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/demo")
public class DemoController {

    @GetMapping(value="/{id}",consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<String> getDemoCall(@PathVariable String id){
        return ResponseEntity.ok("Got user with "+id);
    }

    @PostMapping(value="/save",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postDemoCall(@RequestBody String id){
        return ResponseEntity.ok("Created user with "+id);
    }

    @PutMapping(value="/update",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> putDemoCall(@RequestBody String id){
        return ResponseEntity.ok("Updated user with "+id);
    }

    @DeleteMapping(value="/delete",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteDemoCall(@RequestBody String id){
        return ResponseEntity.ok("Deleted user with id "+id);
    }

}
