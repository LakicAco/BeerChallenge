package com.aco.beerchallenge.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MethodJson {

    @JsonProperty("mash_temp")
    private List<MashTempJson> mashTemp;


    public List<MashTempJson> getMashTemp() {
        return mashTemp;
    }

    public void setMashTemp(List<MashTempJson> mashTemp) {
        this.mashTemp = mashTemp;
    }
}
