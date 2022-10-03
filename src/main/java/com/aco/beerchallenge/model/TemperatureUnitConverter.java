package com.aco.beerchallenge.model;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TemperatureUnitConverter implements AttributeConverter<TemperatureUnit, String> {

    @Override
    public String convertToDatabaseColumn(TemperatureUnit temperatureUnit) {
        if (temperatureUnit == null) {
            return null;
        }
        return temperatureUnit.getKey();
    }

    @Override
    public TemperatureUnit convertToEntityAttribute(String key) {
        if (key == null) {
            return null;
        }

        return Stream.of(TemperatureUnit.values())
                .filter(c -> c.getKey().equals(key))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}