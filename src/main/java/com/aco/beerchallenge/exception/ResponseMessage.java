package com.aco.beerchallenge.exception;

import java.io.Serializable;

public class ResponseMessage implements Serializable {

    private final String text;

    public ResponseMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
