package com.sensiblemetrics.api.sqoola.common.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.sensiblemetrics.api.sqoola.common.system.config.SchedulerConfig.DEFAULT_SCHEDULER_PACKAGE;

@Configuration
@EnableScheduling
@ComponentScan(DEFAULT_SCHEDULER_PACKAGE)
@PropertySource("classpath:cron")
public class SchedulerConfig implements SchedulingConfigurer {

    /**
     * Default scheduler packages
     */
    public static final String DEFAULT_SCHEDULER_PACKAGE = "com.wildbeeslabs.sensiblemetrics.sqoola";
    /**
     * Default scheduler pool size
     */
    private static final int DEFAULT_SCHEDULER_POOL_SIZE = 10;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void configureTasks(final ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(DEFAULT_SCHEDULER_POOL_SIZE);
    }
}
