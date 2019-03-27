package com.sensiblemetrics.api.sqoola.common.validation.handler;

import de.pearl.pem.common.validation.model.GeneralParamRequest;
import de.pearl.pem.common.validation.validator.GeneralParamRequestValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * {@link GeneralParamRequest} validation handler
 */
@Component
public class GeneralParamRequestValidationHandler<T extends Serializable> extends GeneralRequestValidationHandler<GeneralParamRequest<T>> {

    /**
     * Default constructor with initial input {@link GeneralParamRequest} class
     *
     * @param clazz - initial input {@link GeneralParamRequest} class
     */
    public GeneralParamRequestValidationHandler(final Class<GeneralParamRequest<T>> clazz) {
        super(clazz, new GeneralParamRequestValidator());
    }

    @Override
    protected Mono<ServerResponse> onValidationErrors(final Errors errors, final GeneralParamRequest<T> invalidBody, final ServerRequest request) {
        return ServerResponse
            .badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(String.format("Validation errors: {%s}", errors.getAllErrors().toString())), String.class);
    }
}
