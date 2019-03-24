package com.sensiblemetrics.api.sqoola.connector.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class ConnectorNeo4jAppLoader {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConnectorNeo4jAppLoader.class, args);
    }
}
