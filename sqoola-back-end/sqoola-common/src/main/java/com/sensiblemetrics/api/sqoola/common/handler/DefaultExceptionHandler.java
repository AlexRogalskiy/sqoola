package com.sensiblemetrics.api.sqoola.common.handler;

import de.pearl.pem.common.exception.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**
 * Default exception handler implementation
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Resource already exist")
    @ExceptionHandler({ResourceAlreadyExistException.class})
    protected ResponseEntity<?> handleResourceAlreadyExistException(final WebRequest req, final ResourceAlreadyExistException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.CONFLICT.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Data access is not permitted")
    @ExceptionHandler({DataAccessException.class})
    protected ResponseEntity<?> handleDataAccessException(final WebRequest req, final DataAccessException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<?> handleBadRequestException(final WebRequest req, final BadRequestException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getRequestURI().substring(req.getContextPath().length()))
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource is not found")
    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<?> handleResourceNotFoundException(final WebRequest req, final ResourceNotFoundException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Empty response")
    @ExceptionHandler({EmptyContentException.class})
    protected ResponseEntity<?> handleEmptyContentException(final WebRequest req, final EmptyContentException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.NO_CONTENT.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.NO_CONTENT);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Parameter type mismatched")
    @ExceptionHandler({TypeMismatchException.class})
    protected ResponseEntity<?> handleTypeMismatchException(final WebRequest req, final TypeMismatchException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data integrity violation")
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<?> handleMethodArgumentNotValidException(final WebRequest req, final MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Validation constraint violation")
    @ExceptionHandler({javax.validation.ConstraintViolationException.class})
    protected ResponseEntity<?> handleConstraintViolationException(final WebRequest req, final javax.validation.ConstraintViolationException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Path variable is not provided")
    @ExceptionHandler({MissingPathVariableException.class})
    protected ResponseEntity<?> handleMissingPathVariableException(final WebRequest req, final MissingPathVariableException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "Method is not supported")
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<?> handleHttpRequestMethodNotSupportedException(final WebRequest req, final HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Url is not valid")
    @ExceptionHandler({URISyntaxException.class, MalformedURLException.class})
    public ResponseEntity<?> handleURISyntaxException(final WebRequest req, final Exception ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Message is invalid")
    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected ResponseEntity<?> handleHttpMessageNotReadableException(final WebRequest req, final HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Media type is not supported")
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    protected ResponseEntity<?> handleHttpMediaTypeNotSupportedException(final WebRequest req, final HttpMediaTypeNotSupportedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot process client request")
    @ExceptionHandler({ServiceException.class})
    protected ResponseEntity<?> handleServiceException(final WebRequest req, final ServiceException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Access is denied")
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<?> handleAccessDeniedException(final WebRequest req, final AccessDeniedException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.FORBIDDEN.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Media type is not acceptable")
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    protected ResponseEntity<?> handleHttpMediaTypeNotAcceptableException(final WebRequest req, final HttpMediaTypeNotAcceptableException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getRequestURI().substring(req.getContextPath().length()))
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "Multipart message is not supported")
    @ExceptionHandler({MultipartException.class})
    protected ResponseEntity<?> handleMultipartException(final WebRequest req, final MultipartException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE, reason = "Max upload size exceeded")
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    protected ResponseEntity<?> handleMaxUploadSizeExceededException(final WebRequest req, final MaxUploadSizeExceededException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "MasterUrl is not valid")
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<?> handleUnknownHostException(final WebRequest req, final UnknownHostException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request (most likely the token is invalid)")
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientErrorException(final WebRequest req, final HttpClientErrorException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid token format")
    @ExceptionHandler(InvalidTokenFormatException.class)
    public ResponseEntity<?> handleInvalidTokenFormatException(final WebRequest req, final InvalidTokenFormatException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot validate OAuth token")
    @ExceptionHandler(OAuthTokenException.class)
    public ResponseEntity<?> handleOAuthTokenException(final WebRequest req, final OAuthTokenException ex) {
        return new ResponseEntity<>(ExceptionView.builder()
                .path(req.getContextPath())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getLocalizedMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
