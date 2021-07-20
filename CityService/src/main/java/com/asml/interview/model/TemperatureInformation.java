package com.asml.interview.model;

import java.time.LocalDateTime;

public class TemperatureInformation {

    private LocalDateTime time;
    private Double temperature;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public TemperatureInformation(LocalDateTime time, Double temperature) {
        this.time = time;
        this.temperature = temperature;
    }
    public TemperatureInformation(){}

}
