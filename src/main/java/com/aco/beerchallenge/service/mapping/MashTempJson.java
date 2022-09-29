package com.aco.beerchallenge.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MashTempJson {

    @JsonProperty("temp")
    private TempJson tempJson;

    private Long duration;

    public TempJson getTempJson() {
        return tempJson;
    }

    public void setTempJson(TempJson tempJson) {
        this.tempJson = tempJson;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
