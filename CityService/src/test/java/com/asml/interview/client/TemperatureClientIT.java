package com.asml.interview.client;

import com.asml.interview.model.TemperatureInformation;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {FeignConfig.class, MockedService.class}
        , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemperatureClientIT {

    @Autowired
    private TemperatureClient temperatureClient;


    @Test
    public void whenGetTemp_thenTemperatureInformationShouldBeReturned() {
        //given:
        //when:
        TemperatureInformation result = temperatureClient.getTemperatureByCity("Eindhoven");
        //then:
        assertNotNull(result);
        assertEquals(Instant.parse("2019-10-12T07:20:50.52Z"), result.getTime());
        assertEquals(105.1, result.getTemperature());
    }

    @Test
    public void whenGetTemp_throws503() {
        //given:
        //when:
        FeignException exception = Assertions.assertThrows(FeignException.class, () -> {
            temperatureClient.getTemperatureByCity("blabla");
        });
        //then:
        assertNotNull(exception);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), exception.status());
    }

    @Test
    public void whenGetTemp_throws404() {
        //given:
        //when:
        FeignException exception = Assertions.assertThrows(FeignException.class, () -> {
            temperatureClient.getTemperatureByCity("Venlo");
        });
        //then:
        assertNotNull(exception);
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.status());
    }

    @Test
    public void whenGetTemp_throws500() {
        //given:
        //when:
        FeignException exception = Assertions.assertThrows(FeignException.class, () -> {
            temperatureClient.getTemperatureByCity("500");
        });
        //then:
        assertNotNull(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.status());
    }

}
