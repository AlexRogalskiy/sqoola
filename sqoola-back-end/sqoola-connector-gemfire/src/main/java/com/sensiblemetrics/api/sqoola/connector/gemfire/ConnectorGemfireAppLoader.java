package com.sensiblemetrics.api.sqoola.connector.gemfire;

import com.sensiblemetrics.api.sqoola.connector.gemfire.model.Person;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.io.IOException;

@SpringBootApplication
@ClientCacheApplication(name = "AccessingDataGemFireApplication", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = Person.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories
public class ConnectorGemfireAppLoader {

    public static void main(final String[] args) throws IOException {
        SpringApplication.run(ConnectorGemfireAppLoader.class, args);
    }
}
