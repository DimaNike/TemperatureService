package com.asml.interview.temp.exception;

import com.asml.interview.temp.controller.TemperatureController;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String cityName) {
        super(String.format(TemperatureController.RESPONSE_404, cityName));
    }
}
