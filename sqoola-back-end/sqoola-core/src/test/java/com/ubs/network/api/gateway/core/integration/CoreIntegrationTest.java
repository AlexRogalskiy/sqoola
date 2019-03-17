package com.ubs.network.api.gateway.core.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ubs.network.api.rest.common.integration.ABaseIntegrationTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Web integration test using the {@code hateoas} profile.
 *
 */
@ActiveProfiles("hateoas")
//@ActiveProfiles("web")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoreIntegrationTest extends ABaseIntegrationTest {

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

    @Test
    public void envEndpointNotHidden() throws Exception {
        Traverson traverson = new Traverson(new URI("http://localhost:" + this.port + "/greeting"), MediaTypes.HAL_JSON);
        String greeting = traverson.follow("self").toObject("$.content");
        assertThat(greeting).isEqualTo("Hello, World!");
    }

    @Test
    public final void whenSecuredRestApiIsConsumed_then200OK() {
        final ResponseEntity<Foo> responseEntity = secureRestTemplate.exchange("http://localhost:8082/spring-security-rest-basic-auth/api/foos/1", HttpMethod.GET, null, Foo.class);
        assertThat(responseEntity.getStatusCode().value(), is(200));
    }

    @Test(expected = ResourceAccessException.class)
    public final void whenHttpsUrlIsConsumed_thenException() {
        final String urlOverHttps = "https://localhost:8443/spring-security-rest-basic-auth/api/bars/1";
        final ResponseEntity<String> response = new RestTemplate().exchange(urlOverHttps, HttpMethod.GET, null, String.class);
        assertThat(response.getStatusCode().value(), equalTo(200));
    }

    public static final String URL = "http://localhost";

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void whenStartingApplication_thenCorrectStatusCode() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().is2xxSuccessful());
    }

    ;

    @Test
    public void whenAddingNewCorrectUser_thenCorrectStatusCodeAndResponse() throws Exception {
        WebsiteUser user = new WebsiteUser();
        user.setEmail("john.doe@john.com");
        user.setName("John Doe");

        mockMvc.perform(post("/users", user).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user))).andExpect(status().is2xxSuccessful()).andExpect(redirectedUrl("http://localhost/users/1"));
    }

    @Test
    public void whenAddingNewUserWithoutName_thenErrorStatusCodeAndResponse() throws Exception {
        WebsiteUser user = new WebsiteUser();
        user.setEmail("john.doe@john.com");

        mockMvc.perform(post("/users", user).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user))).andExpect(status().isNotAcceptable()).andExpect(redirectedUrl(null));
    }

    @Test
    public void whenAddingNewUserWithEmptyName_thenErrorStatusCodeAndResponse() throws Exception {
        WebsiteUser user = new WebsiteUser();
        user.setEmail("john.doe@john.com");
        user.setName("");
        mockMvc.perform(post("/users", user).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user))).andExpect(status().isNotAcceptable()).andExpect(redirectedUrl(null));
    }

    @Test
    public void whenAddingNewUserWithoutEmail_thenErrorStatusCodeAndResponse() throws Exception {
        WebsiteUser user = new WebsiteUser();
        user.setName("John Doe");

        mockMvc.perform(post("/users", user).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user))).andExpect(status().isNotAcceptable()).andExpect(redirectedUrl(null));
    }

    @Test
    public void whenAddingNewUserWithEmptyEmail_thenErrorStatusCodeAndResponse() throws Exception {
        WebsiteUser user = new WebsiteUser();
        user.setName("John Doe");
        user.setEmail("");
        mockMvc.perform(post("/users", user).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user))).andExpect(status().isNotAcceptable()).andExpect(redirectedUrl(null));
    }
}
