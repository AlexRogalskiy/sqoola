/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ubs.network.api.gateway.core.configuration;

import com.wildbeeslabs.api.rest.common.service.interfaces.IPropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *
 * Application Thyme Leaf Configuration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Configuration("subscriptionThymeleafConfiguration")
public class ThymeleafConfiguration {

    @Autowired
    @Qualifier("subscriptionPropertiesConfiguration")
    private IPropertiesConfiguration propertyConfig;

    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix(propertyConfig.getProperty("spring.application.thymeleaf.prefix"));
        resolver.setSuffix(propertyConfig.getProperty("spring.application.thymeleaf.suffix"));
        resolver.setTemplateMode(propertyConfig.getProperty("spring.application.thymeleaf.mode"));
        resolver.setCharacterEncoding(propertyConfig.getProperty("spring.application.thymeleaf.encoding"));
        resolver.setOrder(propertyConfig.getProperty("spring.application.thymeleaf.order", Integer.class));
        resolver.setCacheable(propertyConfig.getProperty("spring.application.thymeleaf.cache", Boolean.class));
        return resolver;
    }

//    @Bean
//    public ConditionalCommentsDialect conditionalCommentDialect() {
//        return new ConditionalCommentsDialect();
//    }
}
