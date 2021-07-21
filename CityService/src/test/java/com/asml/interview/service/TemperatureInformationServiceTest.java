package com.asml.interview.service;

import com.asml.interview.client.TemperatureClient;
import com.asml.interview.config.ApplicationConfiguration;
import com.asml.interview.dto.City;
import com.asml.interview.model.TemperatureInformation;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TemperatureInformationServiceTest {

    @Mock
    private TemperatureClient temperatureClient;
    @Mock
    private CityService cityService;
    @Mock
    private ApplicationConfiguration applicationConfiguration;
    private TemperatureInformationService temperatureInformationService;
    @BeforeEach
    public void setUp() {
        temperatureInformationService = new TemperatureInformationService(temperatureClient, cityService, applicationConfiguration);
    }

    @Test
    public void getCityTemperature(){
        //given:
        City city = new City();
        city.setName("test");
        List<City> cities = Collections.singletonList(city);
        when(cityService.getAllCities()).thenReturn(cities);
        TemperatureInformation ti = new TemperatureInformation();
        ti.setTemperature(55.55);
        Instant now = Instant.now();
        ti.setTime(now);
        when(temperatureClient.getTemperatureByCity(city.getName())).thenReturn(ti);

        //when:
        temperatureInformationService.getCityTemperature();
        //then:
        verify(cityService).getAllCities();
        verify(temperatureClient).getTemperatureByCity(city.getName());
    }

    @Test
    public void getCityTemperature_temperatureClientThrowsException_theCityGetsRemoved(){
        //given:
        City city = new City();
        city.setName("test");
        city.setId(123L);
        List<City> cities = Collections.singletonList(city);
        when(cityService.getAllCities()).thenReturn(cities);
        Request request = Request.create(Request.HttpMethod.GET, "url",
            new HashMap<>(), null, new RequestTemplate());
        when(temperatureClient.getTemperatureByCity(city.getName())).thenThrow(new FeignException.NotFound("test",request,null));

        //when:
            temperatureInformationService.getCityTemperature();
        //then:
        verify(cityService).removeCity(city.getId());
    }
}
