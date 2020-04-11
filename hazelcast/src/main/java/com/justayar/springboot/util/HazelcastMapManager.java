package com.justayar.springboot.util;

import com.hazelcast.config.Config;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.justayar.springboot.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HazelcastMapManager implements InitializingBean {

    @Autowired
    private Config hazelcastConfig;

    @Autowired
    private EntryListener mapEntryListener;

    @Autowired
    private ApplicationMembershipListener applicationMembershipListener;

    private IMap<String,String> hazelcastMap;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void afterPropertiesSet(){

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(hazelcastConfig);

        hazelcastInstance.getCluster().addMembershipListener(applicationMembershipListener);

        hazelcastMap = hazelcastInstance.getMap(ApplicationConstants.HAZELCAST_MAP);
        hazelcastMap.addEntryListener(mapEntryListener,true);
        logger.info("Getting Hazelcast map from Hazelcast");
    }

    public void putToMap(String key,String value){

        if(key == null)
            throw new NullPointerException("Key of element which is added to map cannot be null");

        if(key.isEmpty())
            throw new IllegalArgumentException("Key of element which is added to map cannot be empty");


        hazelcastMap.set(key, value);
        logger.info("New data added to hazelcast map with key: {}", key);
    }

    public String getMapItemWithKey(String key){

        if(key == null)
            throw new NullPointerException("Key of element which is read from map cannot be null");

        if(key.isEmpty())
            throw new IllegalArgumentException("Key of element which is read from map cannot be empty");

        logger.info("Getting data item from hazelcast map with key: {}", key);
        return hazelcastMap.get(key);
    }

    public Map<String, String> getAllMap(){

        return hazelcastMap;
    }

    public void removeMapItemWithKey(String key){

        if(key == null)
            throw new NullPointerException("Key of element which is removed from map cannot be null");

        if(key.isEmpty())
            throw new IllegalArgumentException("Key of element which is removed from map cannot be empty");

        hazelcastMap.remove(key);
        logger.info("Remove item from hazelcast map succeeded with key: {}", key);
    }

    public void clearAllMap(){

        if(hazelcastMap.size()>0){
            hazelcastMap.clear();
            logger.info("Remove all item from hazelcast map succeeded");

        }
    }




}
