package com.sensiblemetrics.api.sqoola.common.exception;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Token format {@link Exception} implementation
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid token format")
public class InvalidTokenFormatException extends Exception {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -1152846608244718090L;

    public InvalidTokenFormatException(final String message) {
        super(message);
    }

    public InvalidTokenFormatException(final Throwable cause) {
        super(cause);
    }

    public InvalidTokenFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
