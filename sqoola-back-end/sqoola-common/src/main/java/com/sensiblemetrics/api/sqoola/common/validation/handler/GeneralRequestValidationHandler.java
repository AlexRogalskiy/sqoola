package com.sensiblemetrics.api.sqoola.common.validation.handler;

import de.pearl.pem.common.validation.model.GeneralRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GeneralRequestValidationHandler<T extends GeneralRequest<?>> extends AbstractValidationHandler<T, Validator> {

    /**
     * Default constructor with initial input {@link Class} instance
     *
     * @param clazz - initial input {@link Class} instance
     */
    public GeneralRequestValidationHandler(final Class<T> clazz, final Validator validator) {
        super(clazz, validator);
    }

    @Override
    protected Mono<ServerResponse> processBody(final T validBody, final ServerRequest originalRequest) {
        final String responseBody = String.format("{%s}: items=[%s], page=[%s]", getClass().getCanonicalName(), validBody.getItems(), validBody.getPage());
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(responseBody), String.class);
    }
}
