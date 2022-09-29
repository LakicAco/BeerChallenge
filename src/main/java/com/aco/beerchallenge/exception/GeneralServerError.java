package com.aco.beerchallenge.exception;

public class GeneralServerError extends RuntimeException {

    private final String message;
    private final Class<?> type;

    public GeneralServerError(String message, Class<?> type) {
        this.message = message;
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
