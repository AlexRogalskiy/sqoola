package com.sensiblemetrics.api.sqoola.common.validation.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Abstract validator handler
 *
 * @param <T> type of validation {@link Class}
 * @param <U> type of {@link Validator}
 */
public abstract class AbstractValidationHandler<T, U extends Validator> {

    private final Class<T> validationClass;

    private final U validator;

    protected AbstractValidationHandler(final Class<T> clazz, final U validator) {
        this.validationClass = clazz;
        this.validator = validator;
    }

    public final Mono<ServerResponse> handleRequest(final ServerRequest request) {
        return request.bodyToMono(this.validationClass)
            .flatMap(body -> {
                final Errors errors = new BeanPropertyBindingResult(body, this.validationClass.getName());
                this.validator.validate(body, errors);

                if (Objects.isNull(errors) || errors.getAllErrors().isEmpty()) {
                    return processBody(body, request);
                }
                return onValidationErrors(errors, body, request);
            });
    }

    protected Mono<ServerResponse> onValidationErrors(final Errors errors, final T invalidBody, final ServerRequest request) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().toString());
    }

    abstract protected Mono<ServerResponse> processBody(final T validBody, final ServerRequest originalRequest);
}
