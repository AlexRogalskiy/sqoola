package com.sensiblemetrics.api.sqoola.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Josh Cummings
 */
@SpringBootApplication
public class AuthAppLoader {

    public static void main(final String[] args) {
        SpringApplication.run(AuthAppLoader.class, args);
    }
}
