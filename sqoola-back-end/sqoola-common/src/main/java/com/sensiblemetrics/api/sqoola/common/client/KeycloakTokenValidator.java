package com.sensiblemetrics.api.sqoola.common.client;

import com.sensiblemetrics.api.sqoola.common.exception.InvalidTokenFormatException;
import org.apache.commons.lang3.StringUtils;

public class KeycloakTokenValidator {
    private static final String BEARER_PREFIX = "Bearer ";

    public static void validate(final String keycloakToken) throws InvalidTokenFormatException {
        if (!isValid(keycloakToken)) {
            throw new InvalidTokenFormatException("Keycloak token must have 'Bearer ' prefix");
        }
    }

    private static boolean isValid(final String keycloakToken) {
        return (StringUtils.isNotBlank(keycloakToken) && keycloakToken.startsWith(BEARER_PREFIX));
    }
}
