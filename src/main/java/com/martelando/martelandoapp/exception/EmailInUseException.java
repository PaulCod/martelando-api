package com.martelando.martelandoapp.exception;

public class EmailInUseException extends RuntimeException {
    public EmailInUseException(String email) {
        super("Email "+ email + " ja esta em uso");
    }
}
