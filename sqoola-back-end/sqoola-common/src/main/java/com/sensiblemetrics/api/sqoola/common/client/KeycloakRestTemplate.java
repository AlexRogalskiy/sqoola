package com.sensiblemetrics.api.sqoola.common.client;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class KeycloakRestTemplate extends RestTemplate {

    public KeycloakRestTemplate(final String keycloakToken) {
        if (StringUtils.isNotBlank(keycloakToken)) {
            this.setInterceptors(Collections.singletonList(new KeycloakInterceptor(keycloakToken)));
        }
    }
}
