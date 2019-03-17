package com.ubs.network.api.gateway.services.jenkins.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ubs.network.api.rest.common.integration.ABaseIntegrationTest;
import org.junit.Test;
import org.springframework.hateoas.sample.AbstractWebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Web integration test using the {@code hateoas} profile.
 *
 */
@ActiveProfiles("hateoas")
//@ActiveProfiles("web")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JenkinsIntegrationTest extends ABaseIntegrationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }

    @Test
    public void foo() throws Exception {
        mvc.perform(get("/customers/1")).andExpect(status().isOk());
    }

    @Test
    public void corsWithAnnotation() throws Exception {
        ResponseEntity<Greeting> entity = this.restTemplate.exchange(
                RequestEntity.get(uri("/greeting")).header(HttpHeaders.ORIGIN, "http://localhost:9000").build(),
                Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("http://localhost:9000", entity.getHeaders().getAccessControlAllowOrigin());
        Greeting greeting = entity.getBody();
        assertEquals("Hello, World!", greeting.getContent());
    }

    @Test
    public void corsWithJavaconfig() {
        ResponseEntity<Greeting> entity = this.restTemplate.exchange(
                RequestEntity.get(uri("/greeting-javaconfig")).header(HttpHeaders.ORIGIN, "http://localhost:9000").build(),
                Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("http://localhost:9000", entity.getHeaders().getAccessControlAllowOrigin());
        Greeting greeting = entity.getBody();
        assertEquals("Hello, World!", greeting.getContent());
    }

}

//Poll newPoll = new Poll();
//
//		Set<Options> options = new HashSet<>();
//		newPoll.setOptions(options);
//
//		Option option1 = new Option();
//		option1.setValue("Red");
//		options.add(option1);
//
//		Option option2 = new Option();
//		option2.setValue("Blue");
//		options.add(option2);
//URI pollLocation = client.createPoll(newPoll);