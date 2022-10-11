package com.aco.beerchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Collections;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BeerNotFoundException.class)
    protected ResponseEntity<Object> handleBeerNotFoundException(BeerNotFoundException exception, WebRequest webRequest) {
        ResponseMessage bodyOfResponse = new ResponseMessage(exception.getMessage());
        return createResponseEntity(HttpStatus.NOT_FOUND, false, bodyOfResponse);
    }

    @ExceptionHandler(NoBeersException.class)
    protected ResponseEntity<Object> handleNoBeersException(NoBeersException exception, WebRequest webRequest) {
        ResponseMessage bodyOfResponse = new ResponseMessage(exception.getMessage());
        return createResponseEntity(HttpStatus.NOT_FOUND, false, bodyOfResponse);
    }
    private ResponseEntity<Object> createResponseEntity(final HttpStatus httpStatus, final boolean status, final ResponseMessage responseMessage) {
        final ResponseStatus responseStatus = new ResponseStatus(status, Collections.singletonList(responseMessage), OffsetDateTime.now());
        return new ResponseEntity<>(responseStatus, httpStatus);
    }
}
