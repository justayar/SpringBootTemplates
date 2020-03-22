package com.justayar.springboot.util;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapEntryListener implements EntryListener {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void entryAdded(EntryEvent entryEvent) {

        logger.info("New object added to map with key {} and value {}",entryEvent.getKey(),entryEvent.getValue());
    }

    @Override
    public void entryEvicted(EntryEvent entryEvent) {

        logger.info("Map Entry was evicted : {}",entryEvent);

    }

    @Override
    public void entryRemoved(EntryEvent entryEvent) {

        logger.info("Object with key {} removed from map.",entryEvent.getKey());

    }

    @Override
    public void entryUpdated(EntryEvent entryEvent) {

        logger.info("Object with key {} updated from {} to {}.", entryEvent.getKey(),entryEvent.getOldValue(),entryEvent.getValue());

    }

    @Override
    public void mapCleared(MapEvent mapEvent) {

        logger.info("Map was cleared : {}",mapEvent);


    }

    @Override
    public void mapEvicted(MapEvent mapEvent) {

        logger.info("Map was evicted: {}",mapEvent);

    }
}
