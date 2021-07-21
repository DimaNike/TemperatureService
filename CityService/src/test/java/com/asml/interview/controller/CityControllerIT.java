package com.asml.interview.controller;

import com.asml.interview.CityServiceApplication;
import com.asml.interview.dto.City;
import com.asml.interview.model.CityModel;
import com.asml.interview.repository.CityRepository;
import com.asml.interview.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CityServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIT {

    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityService cityService;

    @Test
    public void whenAddCity_theCityIsCreated() {
        //given:
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String cityName = "Eindhoven";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", cityName);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //when:
        URI result = restTemplate.postForLocation("/addCity", request);
        //then:
        assertNotNull(result);
        assertTrue(result.toString().contains("/index"));
        CityModel createdCityModel = cityRepository.findCityModelByName(cityName);
        assertNotNull(createdCityModel);
        assertEquals(cityName, createdCityModel.getName());
    }

    @Test
    public void whenShowAddCityForm_addCityFormReturned() {
        //given:
        //when:
        String result = restTemplate.getForObject("/addCityForm", String.class);
        //then:
        assertNotNull(result);
        assertTrue(result.contains("<input type=\"submit\" value=\"Add City\">"));
    }

    @Test
    public void whenDeleteCity_cityIsRemoved() {
        //given:
        City newCity = new City();
        newCity.setName("Veldhoven");
        cityService.addCity(newCity);
        newCity.setName("Venlo");
        cityService.addCity(newCity);
        CityModel cityModel = cityRepository.findCityModelByName(newCity.getName());

        //when:
        restTemplate.getForObject("/delete/{id}", String.class, cityModel.getId());
        //then:
        CityModel shouldPresent = cityRepository.findCityModelByName("Veldhoven");
        CityModel shouldBeRemoved = cityRepository.findCityModelByName(newCity.getName());
        assertNull(shouldBeRemoved);
        assertNotNull(shouldPresent);
    }

    @Test
    public void whenShowUserList_formWithCitiesIsReturned() {
        //given:
        City newCity = new City();
        newCity.setName("Veldhoven");
        cityService.addCity(newCity);
        newCity.setName("Venlo");
        cityService.addCity(newCity);

        //when:
        String result = restTemplate.getForObject("/index", String.class);
        //then:
        assertNotNull(result);
        assertTrue(result.contains("Veldhoven")&&result.contains("Venlo"));
    }

}
