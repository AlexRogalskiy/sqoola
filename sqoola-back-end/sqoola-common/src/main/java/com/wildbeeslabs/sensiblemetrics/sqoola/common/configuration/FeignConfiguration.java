package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration;

import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignConfiguration {

    @Value("${ribbon.ConnectTimeout:250}")
    private int connectTimeout;

    @Value("${ribbon.ReadTimeout:1000}")
    private int readTimeout;

    @Bean
    public Request.Options requestOptions() {
        log.info("Init with connectTimeout:{} readTimeout:{}", connectTimeout, readTimeout);
        return new Request.Options(connectTimeout, readTimeout);
    }
}
