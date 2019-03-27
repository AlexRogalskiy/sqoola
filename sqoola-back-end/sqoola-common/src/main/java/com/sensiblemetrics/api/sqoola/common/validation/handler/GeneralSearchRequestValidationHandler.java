package com.sensiblemetrics.api.sqoola.common.validation.handler;

import de.pearl.pem.common.validation.model.GeneralSearchRequest;
import de.pearl.pem.common.validation.validator.GeneralParamRequestValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * {@link GeneralSearchRequest} validation handler
 */
@Component
public class GeneralSearchRequestValidationHandler<T extends Serializable> extends GeneralRequestValidationHandler<GeneralSearchRequest<T>> {

    /**
     * Default constructor with initial input {@link GeneralSearchRequest} class
     *
     * @param clazz - initial input {@link GeneralSearchRequest} class
     */
    public GeneralSearchRequestValidationHandler(final Class<GeneralSearchRequest<T>> clazz) {
        super(clazz, new GeneralParamRequestValidator());
    }

    @Override
    protected Mono<ServerResponse> onValidationErrors(final Errors errors, final GeneralSearchRequest<T> invalidBody, final ServerRequest request) {
        return ServerResponse
            .badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(String.format("Validation errors: {%s}", errors.getAllErrors().toString())), String.class);
    }
}
