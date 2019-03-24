package com.sensiblemetrics.api.sqoola.connector.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectorMongoDBAppLoader {

    public static void main(String[] args) {
        SpringApplication.run(ConnectorMongoDBAppLoader.class, args);
    }
}
