package com.sensiblemetrics.api.sqoola.common.queue;

import de.pearl.pem.common.controller.AbstractBaseControllerTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * MQueue implementation unit test {@link AbstractBaseControllerTest}
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "file:src/test/resources/application.yml")
@DirtiesContext
public class MQueueTest extends AbstractBaseControllerTest {

    @Value("${pem.queue.url}")
    private String url;

    private WebTestClient client;

    @Before
    public void before() {
        this.client = WebTestClient
            .bindToServer()
            .baseUrl(this.url)
            .build();
        //this.webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void sendExchangeMessageToQueueTest() {
        this.client.post()
            .uri("/queue/NYSE")
            .syncBody("Test Message")
            .exchange()
            .expectStatus().isAccepted();
    }
}
