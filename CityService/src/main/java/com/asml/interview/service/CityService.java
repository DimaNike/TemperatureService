package com.asml.interview.service;

import com.asml.interview.dto.City;
import com.asml.interview.model.CityModel;
import com.asml.interview.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private static final int MAX_CITIES = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void saveCity(City city) {
        Optional<CityModel> foundCityModel = cityRepository.findById(city.getId());
        if (foundCityModel.isPresent()) {
            CityModel cityModel = foundCityModel.get();
            cityModel.setName(city.getName());
            cityRepository.save(cityModel);
        } else {
            if (cityRepository.count() == MAX_CITIES) {
                LOGGER.warn("Number of cities has reached limit: {}",MAX_CITIES);
            } else {
                addCity(city);
            }
        }
    }

    public void addCity(City city) {
        CityModel cityModel = new CityModel();
        cityModel.setName(city.getName());
        cityRepository.save(cityModel);
    }

    public void removeCity(long id) {
        Optional<CityModel> foundCityModel = cityRepository.findById(id);
        if (foundCityModel.isPresent()) {
            CityModel cityModel = foundCityModel.get();
            cityRepository.delete(cityModel);
        }
    }

    public List<City> getAllCities() {
        List<City> result = new ArrayList<>();
        cityRepository.findAll().forEach(entry -> {
            City city = new City();
            city.setId(entry.getId());
            city.setName(entry.getName());
            result.add(city);
        });
        return result;
    }

}
