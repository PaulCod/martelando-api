package com.martelando.martelandoapp.exception;

public class UnauthorizedProductUpdate extends RuntimeException {
    public UnauthorizedProductUpdate(String message) {
        super(message);
    }
}
