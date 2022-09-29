package com.aco.beerchallenge.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerJson {

    @JsonProperty("id")
    private Long internalId;

    private String name;

    private String description;

    @JsonProperty("method")
    private MethodJson methodJson;

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MethodJson getMethodJson() {
        return methodJson;
    }

    public void setMethodJson(MethodJson methodJson) {
        this.methodJson = methodJson;
    }
}
