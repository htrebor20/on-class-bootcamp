package com.pragma.onclass.domain.exception;

public class BadRequestValidationException extends RuntimeException {
    public BadRequestValidationException(String message) {
        super(message);
    }
}
