package com.asml.interview.temp.service;

import com.asml.interview.temp.exception.CityNotFoundException;
import com.asml.interview.temp.model.TemperatureInformation;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class TemperatureService {
    private static final int MIN_TEMP = 50;
    private static final int MAX_TEMP = 100;
    private static final List<String> listOfCities = Arrays.asList("Eindhoven", "Veldhoven", "Venlo", "Amsterdam", "Berlin");

    public TemperatureInformation getTemperatureByCityName(String cityName) {
        Random r = new Random();
        double random = MIN_TEMP + r.nextDouble() * (MAX_TEMP - MIN_TEMP);
        if (listOfCities.contains(cityName)) {
            return new TemperatureInformation(Instant.now(), Double.parseDouble(new DecimalFormat("#.##").format(random).replace(",", ".")));
        } else {
            throw new CityNotFoundException(cityName);
        }
    }
}
