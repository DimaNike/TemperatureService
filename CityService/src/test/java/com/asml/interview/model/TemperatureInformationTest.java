package com.asml.interview.model;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TemperatureInformationTest {

    @Test
    public void setTemperature() {
        //given:
        TemperatureInformation temperatureInformation = new TemperatureInformation(Instant.now(), 123.123);
        //when:
        //then:
        String text = Double.toString(Math.abs(temperatureInformation.getTemperature()));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        assertEquals(1, decimalPlaces);
    }

    @Test
    public void setTemperature_usingSetter() {
        //given:
        TemperatureInformation temperatureInformation = new TemperatureInformation();
        Instant now = Instant.now();
        temperatureInformation.setTime(now);
        temperatureInformation.setTemperature(123.123);
        //when:
        //then:
        String text = Double.toString(Math.abs(temperatureInformation.getTemperature()));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        assertEquals(1, decimalPlaces);
        assertEquals(now, temperatureInformation.getTime());
    }

    @Test
    public void temperatureWasConvertedFromCelsiusToFahrenheit() {
        //given:
        TemperatureInformation temperatureInformation = new TemperatureInformation();
        Instant now = Instant.now();
        temperatureInformation.setTime(now);
        //when:
        temperatureInformation.setTemperature(40.165465468789798);
        //then:
        assertEquals(104.3, temperatureInformation.getTemperature());
    }
}
