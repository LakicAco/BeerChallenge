package com.aco.beerchallenge.exception;

public class BeerNotFoundException extends RuntimeException {

    private final String id;
    private final Class<?> type;
    private final String message;

    public BeerNotFoundException(String id, Class<?> type, String message) {
        this.id = id;
        this.type = type;
        this.message = message;
    }

    public BeerNotFoundException(String name, Class<?> type) {
        this(name, type, "Requested beer not found for id=");
    }

    public String getId() {
        return id;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
