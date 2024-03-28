package com.storerental.rentalstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * Global exception handler for handling InvalidParameterException across all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles ParameterValidationException and returns a ResponseEntity with a Bad Request status code.

     * @return a ResponseEntity containing the error message and status code
     */
    @ExceptionHandler(ParameterValidationException.class)
    public ResponseEntity<String> handleInvalidParameterException(ParameterValidationException ex) {
        String errorMessage= ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
