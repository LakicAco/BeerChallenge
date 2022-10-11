package com.aco.beerchallenge.exception;

public class BeerNotFoundException extends RuntimeException {

    private final Class<?> type;
    private final String message;

    public BeerNotFoundException(Class<?> type, String message) {
        this.type = type;
        this.message = message;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
