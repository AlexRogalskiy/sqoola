package com.sensiblemetrics.api.sqoola.common.exception;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Namespace not found {@link Exception} implementation
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Namespace is not found")
public class NamespaceNotFoundException extends Exception {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 6494142185017394474L;

    public NamespaceNotFoundException(final String message) {
        super(message);
    }

    public NamespaceNotFoundException(final Throwable cause) {
        super(cause);
    }

    public NamespaceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
