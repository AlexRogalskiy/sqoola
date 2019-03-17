package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private Environment env;

    @Bean
    public CacheManager cacheManager() {
        final EhCacheCacheManager cacheManager = new EhCacheCacheManager(cacheManagerFactory().getObject());
        cacheManager.setTransactionAware(true);
        return cacheManager;
    }

    @Bean(destroyMethod = "destroy")
    public EhCacheManagerFactoryBean cacheManagerFactory() {
        final EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource(env.getProperty("triggers.datasource.config.hibernate.cache.configurationResourceName")));
        bean.setShared(true);
        return bean;
    }

//s
}
