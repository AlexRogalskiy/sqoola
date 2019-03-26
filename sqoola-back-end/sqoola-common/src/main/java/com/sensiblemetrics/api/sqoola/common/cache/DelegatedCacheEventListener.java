//package com.wildbeeslabs.sensiblemetrics.sqoola.cache;
//
//import com.dinamexoft.carol.triggers.models.cache.CacheEventEntry;
//import lombok.extern.slf4j.Slf4j;
//import org.ehcache.event.CacheEvent;
//import org.ehcache.event.CacheEventListener;
//
///**
// * Default delegate cache getString messaging implementation
// */
//@Slf4j
//public class DelegatedCacheEventListener implements CacheEventListener<String, CacheEventEntry> {
//
//    @Override
//    public void onEvent(final CacheEvent<? extends String, ? extends CacheEventEntry> cacheEvent) {
//        log.debug(String.format("DelegatedCacheEventListener: getString: {%s}, key: {%s}, old value: {%s}, new value: {%s}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue()));
//    }
//}
