/**
 * Sqoola Common REST API module info
 */
module com.sensiblemetrics.api.sqoola.common {
    requires static lombok;
    requires org.apache.commons.lang3;
    requires slf4j.api;
    requires java.desktop;
    requires java.sql;
    requires org.joda.time;
    requires spring.jdbc;
    requires modelmapper;
    requires spring.security.core;
    requires spring.context;
    requires org.apache.logging.log4j;
    requires spring.boot.autoconfigure;
    requires spring.batch.core;
    requires springfox.swagger2;
    requires spring.boot;
    requires spring.tx;
    requires spring.data.commons;
    requires org.hibernate.orm.core;
    requires java.validation;
    requires spring.data.jpa;
    requires swagger.annotations;
    requires spring.web;
    requires org.aspectj.weaver;
    requires tomcat.embed.core;
    requires spring.data.redis;
    requires jedis;
    requires java.annotation;
    requires spring.core;
    requires spring.beans;
    requires com.fasterxml.jackson.databind;
    requires spring.security.config;
    //requires spring.security.crypto;
    requires spring.security.web;
    requires springfox.spring.web;
    //requires springfox.spi;
    requires springfox.core;
    requires com.google.common;
    requires spring.webmvc;
    requires spring.orm;
    requires com.zaxxer.hikari;
    requires spring.context.support;
    requires jackson.annotations;
    requires com.fasterxml.jackson.core;
    requires spring.hateoas;
    requires com.fasterxml.jackson.dataformat.xml;
    requires spring.data.rest.core;
    requires spring.websocket;
    requires org.hibernate.validator;
    requires spring.cloud.netflix.core;
    requires commons.collections;
    requires java.persistence;
    requires rt;
    requires spring.data.solr;
    requires commons.lang;
    requires spring.cloud.commons;
    requires springfox.spi;
    requires feign.core;
    requires javers.persistence.sql;
    requires javers.core;
    requires spring.cloud.openfeign.core;
    requires passay;
    requires spring.retry;
}
