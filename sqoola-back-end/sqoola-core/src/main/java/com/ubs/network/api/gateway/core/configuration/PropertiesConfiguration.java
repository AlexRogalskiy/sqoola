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

import com.wildbeeslabs.api.rest.common.configuration.BasePropertiesConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Application Properties Configuration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Configuration("subscriptionPropertiesConfiguration")
public class PropertiesConfiguration extends BasePropertiesConfiguration {

    private static String endPointURI;
    private static String restServiceURI;
    private static String appName;
    private static String appLabel;
    private static String appPrefix;
    private static String dsPrefix;

    @Value("${server.endpoint-path}")
    public void setEndPointURI(final String endPointURI) {
        PropertiesConfiguration.endPointURI = endPointURI;
    }

    @Override
    public String getEndPointURI() {
        return PropertiesConfiguration.endPointURI;
    }

    @Value("#{'${server.base-path}'.concat(':').concat('${server.port}').concat('${server.context-path}')}")
    public void setRestServiceURI(final String restServiceURI) {
        PropertiesConfiguration.restServiceURI = restServiceURI;
    }

    @Override
    public String getRestServiceURI() {
        return PropertiesConfiguration.restServiceURI;
    }

    @Value("${spring.application.name}")
    public void setApplicationName(final String appName) {
        PropertiesConfiguration.appName = appName;
    }

    @Override
    public String getApplicationName() {
        return PropertiesConfiguration.appName;
    }

    @Value("${spring.application.label}")
    public void setApplicationLabel(final String appLabel) {
        PropertiesConfiguration.appLabel = appLabel;
    }

    @Override
    public String getApplicationLabel() {
        return PropertiesConfiguration.appLabel;
    }

    @Value("${spring.application.app-prefix}")
    public void setApplicationPrefix(final String appPrefix) {
        PropertiesConfiguration.appPrefix = appPrefix;
    }

    @Override
    public String getApplicationPrefix() {
        return PropertiesConfiguration.appPrefix;
    }

    @Value("${spring.application.ds-prefix}")
    public void setDatasourcePrefix(final String dsPrefix) {
        PropertiesConfiguration.dsPrefix = dsPrefix;
    }

    @Override
    public String getDatasourcePrefix() {
        return PropertiesConfiguration.dsPrefix;
    }
}
