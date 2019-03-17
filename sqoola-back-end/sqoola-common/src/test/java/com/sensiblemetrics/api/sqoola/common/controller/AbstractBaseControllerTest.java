package com.sensiblemetrics.api.sqoola.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.security.Principal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Base class setting up Spring MVC integration testing bootstrapping the core application configuration.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "file:src/test/resources/application.properties")
@DirtiesContext
public abstract class AbstractBaseControllerTest {

    /**
     * Default authentication user name
     */
    public static final String DEFAULT_USERNAME = "USER";

    /**
     * Default authentication principal instance {@link Principal}
     */
    public static final Principal DEFAULT_PRINCIPAL = () -> DEFAULT_USERNAME;

    @Value("${supersolr.solr.server.url}")
    protected String url;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected UserDetailsService userDetailsService;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void bootstrapsWebApp() {
        assertThat(this.mvc, is(notNullValue()));
    }

    protected URI uri(final String path) {
        return this.restTemplate.getRestTemplate().getUriTemplateHandler().expand(path);
    }
}
