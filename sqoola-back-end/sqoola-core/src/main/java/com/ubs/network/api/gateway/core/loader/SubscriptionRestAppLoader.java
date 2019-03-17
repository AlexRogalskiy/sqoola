/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
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
package com.ubs.network.api.gateway.core.loader;

//import de.codecentric.boot.admin.config.EnableAdminServer;
import com.wildbeeslabs.api.rest.subscription.configuration.SecurityConfiguration;
import com.wildbeeslabs.api.rest.subscription.configuration.AppConfiguration;
import com.wildbeeslabs.api.rest.subscription.configuration.CacheConfiguration;
import com.wildbeeslabs.api.rest.subscription.configuration.JpaConfiguration;
import com.wildbeeslabs.api.rest.subscription.configuration.ServerNotifierConfiguration;
import com.wildbeeslabs.api.rest.subscription.configuration.ThymeleafConfiguration;
import com.wildbeeslabs.api.rest.subscription.configuration.ValidatorConfiguration;
import com.wildbeeslabs.api.rest.subscription.configuration.WebConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 *
 * Initial Subscription REST Application class loader
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@SpringBootApplication(scanBasePackages = {"com.wildbeeslabs.api.rest.subscription", "com.wildbeeslabs.api.rest.common"})
//@ComponentScan({"com.wildbeeslabs.api.rest.subscription", "com.wildbeeslabs.api.rest.common"})
//JavaMailConfiguration.class
@Import({JpaConfiguration.class, SecurityConfiguration.class, AppConfiguration.class, WebConfiguration.class, ValidatorConfiguration.class, ThymeleafConfiguration.class, CacheConfiguration.class, ServerNotifierConfiguration.class})
//@EnableDiscoveryClient
@EnableAutoConfiguration
//@EnableAdminServer
public class SubscriptionRestAppLoader extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SubscriptionRestAppLoader.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionRestAppLoader.class, args);
    }
}
