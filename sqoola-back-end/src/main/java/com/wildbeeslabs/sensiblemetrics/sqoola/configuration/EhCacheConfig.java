//package com.wildbeeslabs.sensiblemetrics.sqoola.configuration;
//
//import com.dinamexoft.carol.triggers.cache.DelegatedCacheEventListener;
//import org.ehcache.CacheManager;
//import org.ehcache.PersistentCacheManager;
//import org.ehcache.config.CacheConfiguration;
//import org.ehcache.config.builders.CacheConfigurationBuilder;
//import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
//import org.ehcache.config.builders.CacheManagerBuilder;
//import org.ehcache.config.builders.ResourcePoolsBuilder;
//import org.ehcache.config.units.MemoryUnit;
//import org.ehcache.event.EventType;
//import org.ehcache.expiry.Expirations;
//import org.ehcache.xml.XmlConfiguration;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import java.io.File;
//import java.net.URL;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//public class EhCacheConfig {
//
//    /**
//     * Default cache names
//     */
//    public static final String DEFAULT_IDEMPOTENT_CACHE_NAME = "idempotent";
//    public static final String DEFAULT_CACHE_NAME = "default";
//    public static final String DEFAULT_AGGREGATE_CACHE_NAME = "aggregate";
//
//    @Bean(destroyMethod = "close")
//    public CacheManager cacheConfigurationManager(final @Value("${triggers.ehcache.configurationResourceName}") String fileName) {
//        final URL url = this.getClass().getResource(fileName);
//        final CacheManager cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(url));
//        cacheManager.init();
//        return cacheManager;
//    }
//
//    @Bean
//    public CacheConfiguration<String, Boolean> cacheConfiguration(final @Value("${triggers.ehcache.config.heapSize:200}") Long heapSize,
//                                                                  final @Value("${triggers.ehcache.config.diskSize:100}") Long diskSize,
//                                                                  final @Value("${triggers.ehcache.config.offHeapSize:80}") Long offHeapSize) {
//        final CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder.newEventListenerConfiguration(
//                new DelegatedCacheEventListener(),
//                EventType.EVICTED,
//                EventType.CREATED, EventType.UPDATED, EventType.EXPIRED, EventType.REMOVED)
//                .unordered()
//                .asynchronous();
//        final CacheConfiguration<String, Boolean> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Boolean.class,
//                ResourcePoolsBuilder.newResourcePoolsBuilder()
//                        .heap(heapSize, MemoryUnit.MB)
//                        .disk(diskSize, MemoryUnit.MB, true)
//                        .offheap(offHeapSize, MemoryUnit.MB))
//                .withExpiry(Expirations.noExpiration())
//                //.withExpiry(Expirations.timeToLiveExpiration(Duration.of(60, TimeUnit.SECONDS)))
//                .add(cacheEventListenerConfiguration)
//                .build();
//        return cacheConfiguration;
//    }
//
//    @Bean(destroyMethod = "destroy")
//    public PersistentCacheManager persistentCacheConfigurationManager(final @Value("${triggers.ehcache.config.location:'/.cache'}") String locationPath,
//                                                                      final CacheConfiguration<String, Boolean> cacheConfiguration) {
//        final PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .with(CacheManagerBuilder.persistence(locationPath + File.separator + "persistent"))
//                .withCache(DEFAULT_IDEMPOTENT_CACHE_NAME, cacheConfiguration)
//                .build(true);
//        return persistentCacheManager;
//    }
//}
