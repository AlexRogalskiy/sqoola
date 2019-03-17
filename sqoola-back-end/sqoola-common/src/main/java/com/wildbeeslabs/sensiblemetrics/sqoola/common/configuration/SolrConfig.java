/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration;

import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.Category;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.Order;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.Product;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.service.BaseSimpleSearchService;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.service.impl.BaseSimpleSearchServiceImpl;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.MappingSolrConverter;
import org.springframework.data.solr.core.convert.SolrConverter;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.SolrClientFactory;
import org.springframework.data.solr.server.support.HttpSolrClientFactory;
import org.springframework.data.solr.server.support.HttpSolrClientFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.Optional;

/**
 * Custom solr configuration
 */
@Configuration
@EnableAsync
@EnableSolrRepositories(
    basePackages = {
        "com.wildbeeslabs.sensiblemetrics.supersolr"
    },
    namedQueriesLocation = "classpath:solr-named-queries.properties",
    schemaCreationSupport = true)
@PropertySource("classpath:application.properties")
public class SolrConfig {

    @Bean
    public SolrConverter solrConverter() {
        final MappingSolrConverter solrConverter = new MappingSolrConverter(new SimpleSolrMappingContext());
        //solrConverter.setCustomConversions(new CustomConversions(null, null));
        return solrConverter;
    }

    @Bean
    public SolrClient solrClient(final @Value("${supersolr.solr.server.url}") String baseUrl,
                                 final @Value("${supersolr.solr.timeout}") Integer timeout,
                                 final @Value("${supersolr.solr.socketTimeout}") Integer socketTimeout) {
        final HttpSolrClient solrClient = new HttpSolrClient.Builder()
            .withBaseSolrUrl(baseUrl)
            .withConnectionTimeout(timeout)
            .withSocketTimeout(socketTimeout)
            .allowCompression(true)
            .build();
        solrClient.getInvariantParams().add("commit", "true");
        solrClient.setFollowRedirects(false);
        solrClient.setUseMultiPartPost(true);
        return solrClient;
    }

//    @Bean(name = "embeddedSolrServer")
//    public EmbeddedSolrServerFactoryBean embeddedSolrServerFactoryBean(final @Value("${supersolr.solr.home}") String solrHome) {
//        final EmbeddedSolrServerFactoryBean factory = new EmbeddedSolrServerFactoryBean();
//        factory.setSolrHome(solrHome);
//        return factory;
//    }

    @Bean
    public Credentials credentials(final @Value("${supersolr.solr.username}") String username,
                                   final @Value("${supersolr.solr.password}") String password) {
        return new UsernamePasswordCredentials(username, password);
    }

    @Bean
    public BasicCredentialsProvider credentialsProvider(final Credentials credentials) {
        final BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }

    @Bean
    public SolrClientFactory solrClientFactory(@Qualifier("solrClient") final SolrClient solrClient, final Credentials credentials) {
        return new HttpSolrClientFactory(solrClient, credentials, "BASIC");
        //return new MulticoreSolrClientFactory(solrClient());
    }

    @Bean
    public CloudSolrClient solrClient(final @Value("${supersolr.solr.zkHost}") String zkHost) {
        final CloudSolrClient solrClient = new CloudSolrClient.Builder(Arrays.asList(zkHost), Optional.empty()).build();
        solrClient.setDefaultCollection("");
        return solrClient;
    }

    @Bean(name = "httpSolrClient")
    public HttpSolrClientFactoryBean httpSolrClientFactoryBean(final @Value("${supersolr.solr.server.url}") String baseUrl,
                                                               final @Value("${supersolr.solr.timeout}") Integer timeout,
                                                               final @Value("${supersolr.solr.maxConnections}") Integer maxConnections,
                                                               final @Qualifier("solrClient") SolrClient solrClient) {
        final HttpSolrClientFactoryBean factory = new HttpSolrClientFactoryBean();
        factory.setUrl(baseUrl);
        factory.setTimeout(timeout);
        factory.setMaxConnections(maxConnections);
        factory.setSolrClient(solrClient);
        return factory;
    }

    @Bean
    public BaseSimpleSearchService<Product, String> productSimpleSearchService(final SolrTemplate solrTemplate) {
        return new BaseSimpleSearchServiceImpl<>(solrTemplate, Product.class);
    }

    @Bean
    public BaseSimpleSearchService<Order, String> orderSimpleSearchService(final SolrTemplate solrTemplate) {
        return new BaseSimpleSearchServiceImpl<>(solrTemplate, Order.class);
    }

    @Bean
    public BaseSimpleSearchService<Category, String> categorySimpleSearchService(final SolrTemplate solrTemplate) {
        return new BaseSimpleSearchServiceImpl<>(solrTemplate, Category.class);
    }

    @Bean
    public SolrTemplate solrTemplate(final @Qualifier("solrClient") SolrClient solrClient) {
        final SolrTemplate solrTemplate = new SolrTemplate(solrClient);
        solrTemplate.setSolrConverter(solrConverter());
        return solrTemplate;
    }
}
