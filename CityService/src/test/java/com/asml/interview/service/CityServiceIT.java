package com.asml.interview.service;

import com.asml.interview.CityServiceApplication;
import com.asml.interview.dto.City;
import com.asml.interview.model.CityModel;
import com.asml.interview.repository.CityRepository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CityServiceApplication.class)
public class CityServiceIT {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void addCity() {
        //given:
        City city = new City();
        city.setName("test");
        //when:
        cityService.addCity(city);
        //then:
        CityModel result = cityRepository.findCityModelByName(city.getName());
        assertNotNull(result);
        assertEquals(city.getName(), result.getName());
    }
    @Test
    public void removeCity(){
        //given:
        City city = new City();
        city.setName("test");
        City cityToBeRemoved = new City();
        cityToBeRemoved.setName("toBeRemoved");
        cityService.addCity(city);
        cityService.addCity(cityToBeRemoved);
        CityModel cityModelToBeRemoved = cityRepository.findCityModelByName(cityToBeRemoved.getName());
        //when:
        cityService.removeCity(cityModelToBeRemoved.getId());
        //then:
        CityModel result = cityRepository.findCityModelByName(cityModelToBeRemoved.getName());
        assertNull(result);
    }

    @Test
    public void getAllCities(){
        //given:
        City city = new City();
        city.setName("findall");
        cityService.addCity(city);
        //when:
        List<City> result = cityService.getAllCities();
        //then:
        assertNotNull(result);
        assertTrue(result.size()>0);
    }
}
