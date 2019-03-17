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
package com.ubs.network.api.gateway.core.integration;

import com.wildbeeslabs.api.rest.common.service.interfaces.IPropertiesConfiguration;
import com.wildbeeslabs.api.rest.subscription.SubscriptionRestAppLoader;
import com.wildbeeslabs.api.rest.subscription.controller.SubscriptionController;
import com.wildbeeslabs.api.rest.subscription.controller.UserController;
import com.wildbeeslabs.api.rest.subscription.controller.UserSubscriptionController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * BaseController REST Application Test
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SubscriptionRestAppLoader.class, UserController.class, SubscriptionController.class, UserSubscriptionController.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class BaseControllerTest {

    /**
     * Default Logger instance
     */
    @Autowired
    private Logger loggerMapper;
    @Autowired
    @Qualifier("subscriptionPropertiesConfiguration")
    private IPropertiesConfiguration propsConfiguration;

    protected String getObjectAsString(final Object obj) {
        final ObjectMapper mapper = new ObjectMapper();
        String value = null;
        try {
            value = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            getLogger().error("Cannot serialize entities, message={}", ex.getMessage());
        }
        return value;
    }

    @Before
    public void setUp() {
        //RestAssured.authentication = preemptive().basic("user", "123");
        getLogger().debug("Initialize Test suite environment");
    }

    protected String getServiceURI() {
        return propsConfiguration.getRestServiceURI();
    }

    protected Logger getLogger() {
        return loggerMapper;
    }

    protected IPropertiesConfiguration getPropertyConfig() {
        return propsConfiguration;
    }
}
