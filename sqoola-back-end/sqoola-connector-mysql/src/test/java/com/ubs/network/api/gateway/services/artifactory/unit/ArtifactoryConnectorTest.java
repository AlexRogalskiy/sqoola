package com.ubs.network.api.gateway.services.artifactory.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
//@DataJpaTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { com.ubs.network.api.rest.services.artifactory.loader.AppLoader })
public class ArtifactoryConnectorTest {

	@After
	public void init(){
	}

	@Before
	public void destroy(){
	}

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}

}
