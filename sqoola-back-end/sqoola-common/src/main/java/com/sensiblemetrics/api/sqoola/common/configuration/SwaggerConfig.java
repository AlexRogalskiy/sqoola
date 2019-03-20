/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
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
package com.sensiblemetrics.api.sqoola.common.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger configuration
 */
@Configuration
public class SwaggerConfig {

    /**
     * Default resource management tag
     */
    public static final String TAG_RESOURCE_MANAGEMENT = "Resource Management";
    /**
     * Default main functional tag
     */
    public static final String TAG_MAIN_FUNCTIONAL = "Main functional";

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .groupName("sqoola")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(Predicates.not(PathSelectors.regex("/error.*")))
            .paths(PathSelectors.regex("/api/.*"))
            .build()
            .tags(
                new Tag(TAG_MAIN_FUNCTIONAL, "All apis relating to main service purpose"),
                new Tag(TAG_RESOURCE_MANAGEMENT, "All apis relating to upload resources"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Sqoola REST API")
            .description("Sqoola API for creating and managing gateway connectors")
            .termsOfServiceUrl("http://example.com/terms-of-service")
            .contact(new Contact("sqoola", "sqoola.io", "info@sqoola.io"))
            .license("MIT License")
            .licenseUrl("http://www.opensource.org/licenses/mit-license.php")
            .version("1.0")
            .build();
    }

//    @Bean
//    public SwaggerSpringMvcPlugin v1APIConfiguration() {
//        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
//        swaggerSpringMvcPlugin
//            .apiInfo(getApiInfo()).apiVersion("1.0")
//            .includePatterns("/v1/*.*").swaggerGroup("v1");
//        swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
//        return swaggerSpringMvcPlugin;
//    }
//
//    @Bean
//    public SwaggerSpringMvcPlugin v2APIConfiguration() {
//        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
//        swaggerSpringMvcPlugin
//            .apiInfo(getApiInfo()).apiVersion("2.0")
//            .includePatterns("/v2/*.*").swaggerGroup("v2");
//        swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
//        return swaggerSpringMvcPlugin;
//    }
//
//    @Bean
//    public SwaggerSpringMvcPlugin v3APIConfiguration() {
//        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
//        swaggerSpringMvcPlugin
//            .apiInfo(getApiInfo()).apiVersion("3.0")
//            .includePatterns("/v3/*.*").swaggerGroup("v3");
//        swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
//        return swaggerSpringMvcPlugin;
//    }
}
