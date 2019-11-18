package com.justayar.springboot.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @PostMapping("/write")
    public String writeToHazelcast(@RequestParam String key,
                                   @RequestParam String value) {


        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcast-map");
        hazelcastMap.put(key, value);
        return "OK";


    }

    @GetMapping("/read")
    public String readFromHazelcast(@RequestParam String key) {


        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcast-map");
        return hazelcastMap.get(key);


    }

    @GetMapping("/readAll")
    public Map<String, String> readAllFromHazelcast() {

        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcast-map");
        return hazelcastMap;


    }

    @DeleteMapping("/remove")
    public String removeFromHazelcast(@RequestParam String key){
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcast-map");
        hazelcastMap.remove(key);
        return "OK";
    }

    @DeleteMapping("/clear")
    public String clearHazelcast(){
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("hazelcast-map");
        hazelcastMap.clear();
        return "OK";
    }


}
