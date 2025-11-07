package com.scaler.demoproductservices2025kesara.exception;

public class UnAuthorizedException extends Exception {
    private String message;
    public UnAuthorizedException(String message) {
        super(message);
        this.message = message;
    }
}
