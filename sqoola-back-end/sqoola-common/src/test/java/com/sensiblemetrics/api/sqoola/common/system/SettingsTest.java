package com.sensiblemetrics.api.sqoola.common.system;

import com.sensiblemetrics.api.sqoola.common.annotation.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@TestPropertySource(locations = "classpath:SettingsTest.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
public class SettingsTest {

    @Autowired
    private TestSettings settings;

    @Test
    public void testConfig() {
        assertEquals("TEST_PROPERTY", settings.getProperty());
    }
}
