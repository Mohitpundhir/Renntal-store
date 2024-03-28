package com.storerental.rentalstore.exceptions;
public class ParameterValidationException extends RuntimeException{
    public ParameterValidationException(String message) {
        super(message);
    }
}
