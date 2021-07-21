package com.asml.interview.model;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
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
}
