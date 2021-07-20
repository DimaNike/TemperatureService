package com.asml.interview.model;

import java.text.DecimalFormat;
import java.time.Instant;

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
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        this.temperature = Double.parseDouble(decimalFormat.format(temperature).replace(",", "."));
    }

    public TemperatureInformation(Instant time, Double temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public TemperatureInformation() {
    }

}
