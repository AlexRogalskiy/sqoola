package com.sensiblemetrics.api.sqoola.common.validation.handler;

import de.pearl.pem.common.validation.model.OffsetPageRequest;
import de.pearl.pem.common.validation.validator.OffsetPageRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class OffsetPageHandler {

    public Mono<ServerResponse> handleRequest(final ServerRequest request) {
        final Validator validator = new OffsetPageRequestValidator();
        final Mono<String> responseBody = request.bodyToMono(OffsetPageRequest.class)
            .map(body -> {
                final Errors errors = new BeanPropertyBindingResult(body, OffsetPageRequest.class.getName());
                validator.validate(body, errors);

                if (Objects.isNull(errors) || errors.getAllErrors().isEmpty()) {
                    return String.format("{%s}: offset: [%s], page number: [%s], page size: [%s], sort: [%s]", getClass().getCanonicalName(), body.getOffset(), body.getPageNumber(), body.getPageSize(), body.getSort());
                }
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().toString());
            });
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseBody, String.class);
    }
}
