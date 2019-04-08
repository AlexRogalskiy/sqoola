package com.sensiblemetrics.api.sqoola.connector.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@EnableMongoAuditing
@SpringBootApplication
public class ConnectorMongoDBAppLoader {

    public static void main(final String[] args) {
        SpringApplication.run(ConnectorMongoDBAppLoader.class, args);
    }
}
