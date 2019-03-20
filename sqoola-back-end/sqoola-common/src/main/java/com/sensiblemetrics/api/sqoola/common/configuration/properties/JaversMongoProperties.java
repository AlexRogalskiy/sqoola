package com.sensiblemetrics.api.sqoola.common.configuration.properties;

import org.javers.spring.JaversSpringProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author pawelszymczyk
 */
@ConfigurationProperties(prefix = "javers")
public class JaversMongoProperties extends JaversSpringProperties {

    private boolean documentDbCompatibilityEnabled = false;

    public boolean isDocumentDbCompatibilityEnabled() {
        return documentDbCompatibilityEnabled;
    }

    public void setDocumentDbCompatibilityEnabled(boolean documentDbCompatibilityEnabled) {
        this.documentDbCompatibilityEnabled = documentDbCompatibilityEnabled;
    }

}
