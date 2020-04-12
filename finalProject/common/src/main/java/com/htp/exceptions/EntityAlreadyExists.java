package com.htp.exceptions;

public class EntityAlreadyExists extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExists(String message) {
        super(message);
    }

    public EntityAlreadyExists(String message, Exception ex) {
        super(message, ex);
    }
}
