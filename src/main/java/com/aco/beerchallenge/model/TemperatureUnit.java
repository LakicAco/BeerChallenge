package com.aco.beerchallenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TemperatureUnit {
    CELSIUS("celsius"),
    FAHREINHEIT("fahrenheit");


    private static final Map<String, TemperatureUnit> LOOKUP = Arrays.stream(values()).collect(Collectors.toMap(TemperatureUnit::getKey, e -> e));

    private final String key;

    private TemperatureUnit(String key) {
        this.key = key;
    }

    @JsonValue
    public String getKey() {
        return key;
    }

    @JsonCreator
    public static TemperatureUnit toEnum(String value) {
        return LOOKUP.get(value);
    }
}
