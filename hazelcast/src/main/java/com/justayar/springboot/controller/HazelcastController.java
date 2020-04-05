package com.justayar.springboot.controller;


import com.justayar.springboot.util.HazelcastMapManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {


    @Autowired
    private HazelcastMapManager hazelcastMapManager;

    @PostMapping("/write")
    public String writeToHazelcast(@RequestParam String key,
                                   @RequestParam String value) {


        hazelcastMapManager.putToMap(key, value);

        return "OK";


    }

    @GetMapping("/read")
    public String readFromHazelcast(@RequestParam String key) {

        return hazelcastMapManager.getMapItemWithKey(key);

    }

    @GetMapping("/readAll")
    public Map<String, String> readAllFromHazelcast() {

        return hazelcastMapManager.getAllMap();


    }

    @DeleteMapping("/remove")
    public String removeFromHazelcast(@RequestParam String key){

        hazelcastMapManager.removeMapItemWithKey(key);
        return "OK";
    }

    @DeleteMapping("/clear")
    public String clearHazelcast(){
        hazelcastMapManager.clearAllMap();
        return "OK";
    }


}
