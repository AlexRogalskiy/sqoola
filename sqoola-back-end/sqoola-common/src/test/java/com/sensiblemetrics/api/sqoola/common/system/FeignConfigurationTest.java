package com.sensiblemetrics.api.sqoola.common.system;

import de.pearl.pem.frontend.AbstractTest;
import feign.Request;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class FeignConfigurationTest extends AbstractTest {

    @Autowired
    private Request.Options requestOptions;

    @Test
    public void test() {
        int ribbonConnectTimeout = 300; // same value is in test application.yaml
        int ribbonReadTimeout = 1000; // it`s default value in FeignConfiguration (readTimeout is not specified in test application.yaml)

        assertEquals(ribbonReadTimeout, requestOptions.readTimeoutMillis());
        assertEquals(ribbonConnectTimeout, requestOptions.connectTimeoutMillis());
    }
}
