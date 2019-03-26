//package com.sensiblemetrics.api.sqoola.common.cache;
//
//import lombok.extern.slf4j.Slf4j;
//import net.sf.ehcache.CacheException;
//import net.sf.ehcache.Ehcache;
//import net.sf.ehcache.Element;
//import net.sf.ehcache.event.CacheEventListenerAdapter;
//
///**
// * Default delegate cache3 event messaging implementation
// */
//@Slf4j
//public class DelegatedCache3EventListener extends CacheEventListenerAdapter {
//
//    @Override
//    public void notifyElementRemoved(final Ehcache cache, final Element element) throws CacheException {
//        log.debug("{}: element removed: cache={}, key={}, creationTime={}, expirationTime={}", getClass(), cache.getName(), element.getObjectKey(), element.getCreationTime(), element.getExpirationTime());
//    }
//
//    @Override
//    public void notifyElementPut(final Ehcache cache, final Element element) throws CacheException {
//        log.debug("{}: element put: cache={}, key={}, creationTime={}, expirationTime={}", getClass(), cache.getName(), element.getObjectKey(), element.getCreationTime(), element.getExpirationTime());
//    }
//
//    @Override
//    public void notifyElementUpdated(final Ehcache cache, final Element element) throws CacheException {
//        log.debug("{}: element updated: cache={}, key={}, creationTime={}, expirationTime={}", getClass(), cache.getName(), element.getObjectKey(), element.getCreationTime(), element.getExpirationTime());
//    }
//
//    @Override
//    public void notifyElementExpired(final Ehcache cache, final Element element) {
//        log.debug("{}: element expired: cache={}, key={}, creationTime={}, expirationTime={}", getClass(), cache.getName(), element.getObjectKey(), element.getCreationTime(), element.getExpirationTime());
//    }
//
//    @Override
//    public void notifyElementEvicted(final Ehcache cache, final Element element) {
//        log.debug("{}: element evicted: cache={}, key={}, creationTime={}, expirationTime={}", getClass(), cache.getName(), element.getObjectKey(), element.getCreationTime(), element.getExpirationTime());
//    }
//
//    @Override
//    public void notifyRemoveAll(final Ehcache cache) {
//        log.debug("{}: removeAll from cache={}", getClass(), cache.getName());
//    }
//
//    @Override
//    public void dispose() {
//        log.debug("{}: dispose()", getClass());
//    }
//
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//}
