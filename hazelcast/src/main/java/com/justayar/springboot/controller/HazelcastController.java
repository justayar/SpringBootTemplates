package com.justayar.springboot.controller;


import com.justayar.springboot.util.HazelcastManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {


    @Autowired
    private HazelcastManager hazelcastManager;

    @PostMapping("/write")
    public String writeToHazelcast(@RequestParam String key,
                                   @RequestParam String value) {


        hazelcastManager.putToMap(key, value);

        return "OK";


    }

    @GetMapping("/read")
    public String readFromHazelcast(@RequestParam String key) {

        return hazelcastManager.getMapItemWithKey(key);

    }

    @GetMapping("/readAll")
    public Map<String, String> readAllFromHazelcast() {

        return hazelcastManager.getAllMap();


    }

    @DeleteMapping("/remove")
    public String removeFromHazelcast(@RequestParam String key){

        hazelcastManager.removeMapItemWithKey(key);
        return "OK";
    }

    @DeleteMapping("/clear")
    public String clearHazelcast(){
        hazelcastManager.clearAllMap();
        return "OK";
    }


}
