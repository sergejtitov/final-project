package com.htp.exceptions;

public class CustomValidationException extends RuntimeException {
    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException(String message, Exception ex) {
        super(message, ex);
    }
}