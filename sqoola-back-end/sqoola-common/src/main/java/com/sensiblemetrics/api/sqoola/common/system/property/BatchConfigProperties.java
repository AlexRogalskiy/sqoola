///*
// * The MIT License
// *
// * Copyright 2019 WildBees Labs, Inc.
// *
// * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in
// * all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// * THE SOFTWARE.
// */
//package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration.properties;
//
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.constraints.NotBlank;
//
///**
// * Custom batch configuration properties
// */
//@Data
//@Configuration
//@EnableConfigurationProperties
//@PropertySource("classpath:application.yml")
//@ConfigurationProperties(ignoreInvalidFields = true, prefix = "supersolr.batch")
//@Validated
//public class BatchConfigProperties {
//
//    /**
//     * Default path pattern
//     */
//    @NotBlank
//    private String pathPattern;
//
//    /**
//     * Default extract path
//     */
//    private String extractPath;
//
//    /**
//     * Default cron scheduling
//     */
//    private String cron;
//}
