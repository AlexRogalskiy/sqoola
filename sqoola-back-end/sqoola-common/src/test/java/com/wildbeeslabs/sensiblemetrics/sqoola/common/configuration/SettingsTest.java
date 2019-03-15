package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.annotation.TestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@TestPropertySource(locations = "classpath:SettingsTest.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
public class SettingsTest {

    @Autowired
    TestSettings settings;

    @Test
    public void testConfig() {
        Assert.assertEquals("TEST_PROPERTY", settings.getProperty());
    }
}
