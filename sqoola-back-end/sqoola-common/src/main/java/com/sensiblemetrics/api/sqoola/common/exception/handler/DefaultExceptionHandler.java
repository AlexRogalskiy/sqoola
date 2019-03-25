/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sensiblemetrics.api.sqoola.common.exception.handler;

import com.sensiblemetrics.api.sqoola.common.exception.*;
import com.sensiblemetrics.api.sqoola.common.model.dto.ExceptionView;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**
 * Default {@link ResponseEntityExceptionHandler} implementation
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {//extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Resource already exist")
    @ExceptionHandler({ResourceAlreadyExistException.class})
    protected ResponseEntity<?> handleResourceAlreadyExistException(final HttpServletRequest req, final ResourceAlreadyExistException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView
            .builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.CONFLICT.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Data access is not permitted")
    @ExceptionHandler({DataAccessException.class})
    protected ResponseEntity<?> handleDataAccessException(final HttpServletRequest req, final DataAccessException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.UNAUTHORIZED.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<?> handleBadRequestException(final HttpServletRequest req, final BadRequestException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource is not found")
    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<?> handleResourceNotFoundException(final HttpServletRequest req, final ResourceNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.NOT_FOUND.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Empty response")
    @ExceptionHandler({EmptyContentException.class})
    protected ResponseEntity<?> handleEmptyContentException(final HttpServletRequest req, final EmptyContentException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.NO_CONTENT.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.NO_CONTENT);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Repository validation constraint is violated")
    @ExceptionHandler({RepositoryConstraintViolationException.class})
    public ResponseEntity<?> handleRepositoryConstraintViolationException(final HttpServletRequest req, final RepositoryConstraintViolationException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.NOT_ACCEPTABLE.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Parameter type mismatched")
    @ExceptionHandler({TypeMismatchException.class})
    protected ResponseEntity<?> handleTypeMismatchException(final HttpServletRequest req, final TypeMismatchException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data integrity violation")
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<?> handleMethodArgumentNotValidException(final HttpServletRequest req, final MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Validation constraint violation")
    @ExceptionHandler({javax.validation.ConstraintViolationException.class})
    protected ResponseEntity<?> handleConstraintViolationException(final HttpServletRequest req, final javax.validation.ConstraintViolationException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Path variable is not provided")
    @ExceptionHandler({MissingPathVariableException.class})
    protected ResponseEntity<?> handleMissingPathVariableException(final HttpServletRequest req, final MissingPathVariableException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "Method is not supported")
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<?> handleHttpRequestMethodNotSupportedException(final HttpServletRequest req, final HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.METHOD_NOT_ALLOWED.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Url is not valid")
    @ExceptionHandler({URISyntaxException.class, MalformedURLException.class})
    public ResponseEntity<?> handleURISyntaxException(final HttpServletRequest req, final Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message is invalid")
    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected ResponseEntity<?> handleHttpMessageNotReadableException(final HttpServletRequest req, final HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Media type is not supported")
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    protected ResponseEntity<?> handleHttpMediaTypeNotSupportedException(final HttpServletRequest req, final HttpMediaTypeNotSupportedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot process client request")
    @ExceptionHandler({ServiceException.class})
    protected ResponseEntity<?> handleServiceException(final HttpServletRequest req, final ServiceException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Access is denied")
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<?> handleAccessDeniedException(final HttpServletRequest req, final AccessDeniedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.FORBIDDEN.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Media type is not acceptable")
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    protected ResponseEntity<?> handleHttpMediaTypeNotAcceptableException(final HttpServletRequest req, final HttpMediaTypeNotAcceptableException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Multipart message is not supported")
    @ExceptionHandler({MultipartException.class})
    protected ResponseEntity<?> handleMultipartException(final HttpServletRequest req, final MultipartException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE, reason = "Max upload size exceeded")
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    protected ResponseEntity<?> handleMaxUploadSizeExceededException(final HttpServletRequest req, final MaxUploadSizeExceededException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.PAYLOAD_TOO_LARGE.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "MasterUrl is not valid")
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<?> handleUnknownHostException(final HttpServletRequest req, final UnknownHostException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request (most likely the token is invalid)")
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientErrorException(final HttpServletRequest req, final HttpClientErrorException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Namespace not found")
    @ExceptionHandler(NamespaceNotFoundException.class)
    public ResponseEntity<?> handleNamespaceNotFoundException(final HttpServletRequest req, final NamespaceNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.NOT_FOUND.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.NOT_FOUND);
    }

//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Credentials are invalid")
//    @ExceptionHandler(KubernetesClientException.class)
//    public ResponseEntity<?> handleKubernetesClientException(final HttpServletRequest req, final KubernetesClientException ex) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(ExceptionView.builder()
//            .path(req.getRequestURI().substring(req.getContextPath().length()))
//            .code(HttpStatus.UNAUTHORIZED.value())
//            .message(ex.getLocalizedMessage())
//            .build(), HttpStatus.UNAUTHORIZED);
//    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid token format")
    @ExceptionHandler(InvalidTokenFormatException.class)
    public ResponseEntity<?> handleInvalidTokenFormatException(final HttpServletRequest req, final InvalidTokenFormatException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.BAD_REQUEST.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot validate OAuth token")
    @ExceptionHandler(OAuthTokenException.class)
    public ResponseEntity<?> handleOAuthTokenException(final HttpServletRequest req, final OAuthTokenException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
            .path(req.getRequestURI().substring(req.getContextPath().length()))
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(ex.getLocalizedMessage())
            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
