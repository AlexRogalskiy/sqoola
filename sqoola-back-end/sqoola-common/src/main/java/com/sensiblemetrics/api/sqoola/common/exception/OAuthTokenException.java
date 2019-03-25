package com.sensiblemetrics.api.sqoola.common.exception;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * OAuthToken {@link Exception} implementation
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "OAuth token cannot be recognized")
public class OAuthTokenException extends Exception {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -246867947945678966L;

    public OAuthTokenException(final String message) {
        super(message);
    }

    public OAuthTokenException(final Throwable cause) {
        super(cause);
    }

    public OAuthTokenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
