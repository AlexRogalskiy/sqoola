package com.sensiblemetrics.api.sqoola.common.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * Separate configuration class to enable Spring Hateoas functionality if the {@code hateoas} profile is activated.
 */
@Configuration
//@Profile("hateoas")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class MediaConfiguration {
}
