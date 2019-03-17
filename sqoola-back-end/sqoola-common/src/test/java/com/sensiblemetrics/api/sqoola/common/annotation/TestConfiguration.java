package com.sensiblemetrics.api.sqoola.common.annotation;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.wildbeeslabs.sensiblemetrics.sqoola.common"})
@Configuration
public @interface TestConfiguration {
}
