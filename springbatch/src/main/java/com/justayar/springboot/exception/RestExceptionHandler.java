package com.justayar.springboot.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Value("${covid.service.not.accessible.error.message}")
    private String covidServiceErrorMessage;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new CovidServiceApiError(BAD_REQUEST));
    }

    private ResponseEntity<Object> buildResponseEntity(CovidServiceApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(CovidServiceNotAccessibleException.class)
    protected ResponseEntity<Object> handleCovidServiceNotAccessibleException() {
        CovidServiceApiError apiError = new CovidServiceApiError(BAD_REQUEST);
        apiError.setMessage(covidServiceErrorMessage);
        return buildResponseEntity(apiError);
    }
}