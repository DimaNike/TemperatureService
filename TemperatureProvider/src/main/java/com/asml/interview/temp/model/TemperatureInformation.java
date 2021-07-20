package com.asml.interview.temp.model;

import java.time.Instant;
import java.time.LocalDateTime;

public class TemperatureInformation {

    private Instant time;
    private Double temperature;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public TemperatureInformation(Instant time, Double temperature) {
        this.time = time;
        this.temperature = temperature;
    }

}
