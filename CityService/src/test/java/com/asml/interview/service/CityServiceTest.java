package com.asml.interview.service;

import com.asml.interview.dto.City;
import com.asml.interview.model.CityModel;
import com.asml.interview.repository.CityRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    private CityService cityService;
    @Mock
    private CityRepository cityRepository;

    @BeforeEach
    public void setUp() {
        cityService = new CityService(cityRepository);
    }

    @Test
    public void addCity_theCityWasAdded() {
        //given:
        City city = new City();
        city.setName("test");
        when(cityRepository.count()).thenReturn(3L);
        when(cityRepository.findCityModelByName(city.getName())).thenReturn(null);
        //when:
        cityService.addCity(city);
        //then:
        verify(cityRepository).findCityModelByName(city.getName());
        verify(cityRepository).save(any(CityModel.class));
    }

    @Test
    public void addCity_theCityAlreadyExist() {
        //given:
        City city = new City();
        city.setName("test");
        when(cityRepository.count()).thenReturn(3L);
        when(cityRepository.findCityModelByName(city.getName())).thenReturn(new CityModel());
        //when:
        cityService.addCity(city);
        //then:
        verify(cityRepository).findCityModelByName(city.getName());
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void addCity_reachedMaximumNumberOfCities() {
        //given:
        City city = new City();
        city.setName("test");
        when(cityRepository.count()).thenReturn(5L);
        //when:
        cityService.addCity(city);
        //then:
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void removeCity() {
        //given:
        long id = 123L;
        CityModel cityModel = new CityModel();
        cityModel.setId(id);
        cityModel.setName("test");
        Optional<CityModel> result = Optional.of(cityModel);
        when(cityRepository.findById(id)).thenReturn(result);
        //when:
        cityService.removeCity(id);
        //then:
        verify(cityRepository).delete(cityModel);
    }

    @Test
    public void getAllCities_returnsOneCity() {
        //given:
        CityModel cityModel = new CityModel();
        cityModel.setName("test");
        cityModel.setId(123L);
        List<CityModel> cityModels = Collections.singletonList(cityModel);
        when(cityRepository.findAll()).thenReturn(cityModels);
        //when:
        List<City> result = cityService.getAllCities();
        //then:
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cityModel.getId(), result.get(0).getId());
        assertEquals(cityModel.getName(), result.get(0).getName());
    }
}
