package com.justayar.springboot.util;

import com.hazelcast.core.EntryListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.justayar.springboot.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HazelcastManager implements InitializingBean {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private EntryListener mapEntryListener;

    private IMap<String,String> hazelcastMap;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void afterPropertiesSet() throws Exception {

        hazelcastMap = hazelcastInstance.getMap(ApplicationConstants.HAZELCAST_MAP);
        hazelcastMap.addEntryListener(mapEntryListener,true);
        logger.info("Getting Hazelcast map from Hazelcast");

    }

    public void putToMap(String key,String value){

        if(key != null && !key.isEmpty()){

            hazelcastMap.set(key, value);
            logger.info("New data added to hazelcast map with key: {}", key);

        }

    }

    public String getMapItemWithKey(String key){


        if(key != null && !key.isEmpty()){

            logger.info("Getting data item from hazelcast map with key: {}", key);
            return hazelcastMap.get(key);

        }

        return null;

    }

    public Map<String, String> getAllMap(){

        return hazelcastMap;

    }

    public void removeMapItemWithKey(String key){

        if(key != null && !key.isEmpty()){

            hazelcastMap.remove(key);
            logger.info("Remove item from hazelcast map succeeded with key: {}", key);

        }else{
            logger.info("Remove item from hazelcast map failed with key: {}", key);

        }

    }

    public void clearAllMap(){

        if(hazelcastMap.size()>0){
            hazelcastMap.clear();
            logger.info("Remove all item from hazelcast map succeeded");

        }

    }




}
