package com.sensiblemetrics.api.sqoola.common.controller;//package de.pearl.pem.product.fetcher.system;
//
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.client.support.BasicAuthorizationInterceptor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
//
//    @Value("${core.service.username}")
//    private String username;
//
//    @Value("${core.service.password}")
//    private String password;
//
//    @Value("${core.service.ip}")
//    private String ipAddress;
//
//    @Value("${core.service.portNumber}")
//    private Integer portNumber;
//
//    @Value("${core.service.protocol}")
//    private String protocol;
//
//    private RestTemplate restTemplate;
//
//    public RestTemplate getObject() {
//        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
//        return restTemplate;
//    }
//    public Class<RestTemplate> getObjectType() {
//        return RestTemplate.class;
//    }
//    public boolean isSingleton() {
//        return true;
//    }
//
//    public void afterPropertiesSet() {
//        HttpHost host = new HttpHost(ipAddress, portNumber, protocol);
//        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactoryBasicAuth(host));
//    }
//}
//core.service.baseUrl=http://127.0.0.1:9200
//    core.service.protocol=http
//    core.service.ip=127.0.0.1
//    core.service.portNumber=9200
//
//    # spring boot
//    Prox PreserveHost On
//    RequestHeader set X-Forwarded-Proto https
//    RequestHeader set X-Forwarded-Port 443
//    ProxyPass / http://127.0.0.1:9201/
//    ProxyPassReverse / http://127.0.0.1:9201/
