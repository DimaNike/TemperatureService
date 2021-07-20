package com.asml.interview.service;

import com.asml.interview.client.TemperatureClient;
import com.asml.interview.config.ApplicationConfiguration;
import com.asml.interview.dto.City;
import com.asml.interview.model.TemperatureInformation;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.List;

@Service
public class TemperatureInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureInformationService.class);

    private TemperatureClient temperatureClient;
    private CityService cityService;
    private ApplicationConfiguration applicationConfiguration;

    public TemperatureInformationService(TemperatureClient temperatureClient, CityService cityService, ApplicationConfiguration configuration) {
        this.temperatureClient = temperatureClient;
        this.cityService = cityService;
        this.applicationConfiguration = configuration;
    }

    @Scheduled(fixedRateString = "#{applicationConfiguration.getCronJobRate()}")
    public void getCityTemperature() {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        List<City> allCities = cityService.getAllCities();
        allCities.forEach(entry -> {
            Instant requestTime = Instant.now();
            try {
                TemperatureInformation temperatureInformation = temperatureClient.getTemperatureByCity(entry.getName());
                LOGGER.info("[city: {}, requestTime: {}, temperature: {}, temperatureTime: {}]", entry.getName(), requestTime, temperatureInformation.getTemperature(), temperatureInformation.getTime());
            } catch (FeignException e) {
                LOGGER.error("Unable to get temperature information for the city: {}", entry.getName(), e);
            }
        });
    }

    private static String formatTemperatureValue(Double temperature, DecimalFormat decimalFormat) {
        return decimalFormat.format(temperature).replace(",", ".");
    }
}
