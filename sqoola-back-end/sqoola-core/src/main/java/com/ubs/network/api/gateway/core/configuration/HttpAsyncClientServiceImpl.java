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
package com.ubs.network.api.gateway.core.configuration;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import org.apache.http.Header;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * HTTP Async Client REST Application Service implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Service("httpAsyncClientService")
@Transactional
public class HttpAsyncClientServiceImpl implements IHttpClientService {

//    public CloseableHttpAsyncClient getHttpClient() {
//        return configureHttpClient(getDefaultRequestConfig(), null, null, null, null);
//    }
//
//    public CloseableHttpAsyncClient configureHttpAsyncClient(@Nullable final RequestConfig requestConfig, @Nullable final List<? extends Header> headerList, @Nullable final RedirectStrategy redirectStrategy, @Nullable final CredentialsProvider credentialsProvider, @Nullable final SSLContext sslContext) {
//        HttpClientBuilder client = HttpClientBuilder.create();
//        if (Objects.nonNull(requestConfig)) {
//            client.setDefaultRequestConfig(requestConfig);
//        } else {
//            client.setDefaultRequestConfig(getDefaultRequestConfig());
//        }
//        if (Objects.nonNull(sslContext)) {
//            client.setSSLContext(sslContext);
//            client.setSSLHostnameVerifier(new NoopHostnameVerifier());
//        }
//        if (Objects.nonNull(headerList)) {
//            client.setDefaultHeaders(headerList);
//        }
//        if (Objects.nonNull(credentialsProvider)) {
//            client.setDefaultCredentialsProvider(credentialsProvider);
//        }
//        if (Objects.nonNull(redirectStrategy)) {
//            client.setRedirectStrategy(redirectStrategy);
//        } else {
//            client.disableRedirectHandling();
//        }
//        return client.build();
//    }
}
