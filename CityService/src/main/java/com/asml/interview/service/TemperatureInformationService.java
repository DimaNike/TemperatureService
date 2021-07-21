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

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TemperatureInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureInformationService.class);

    private final TemperatureClient temperatureClient;
    private final CityService cityService;
    private ApplicationConfiguration applicationConfiguration;
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneId.of("UTC"));

    public TemperatureInformationService(TemperatureClient temperatureClient, CityService cityService, ApplicationConfiguration configuration) {
        this.temperatureClient = temperatureClient;
        this.cityService = cityService;
        this.applicationConfiguration = configuration;
    }

    @Scheduled(fixedRateString = "#{applicationConfiguration.getCronJobRate()}")
    public void getCityTemperature() {
        List<City> allCities = cityService.getAllCities();

        allCities.forEach(entry -> {
            Instant requestTime = Instant.now();
            try {
                TemperatureInformation temperatureInformation = temperatureClient.getTemperatureByCity(entry.getName());
                LOGGER.info("[city: {}, requestTime: {}, temperature: {}, temperatureTime: {}]"
                        , entry.getName()
                        , formatter.format(requestTime)
                        , temperatureInformation.getTemperature()
                        , formatter.format(temperatureInformation.getTime()));
            } catch (FeignException e) {
                LOGGER.error("Unable to get temperature information for the city: {}; The city is to be removed", entry.getName(), e);
                cityService.removeCity(entry.getId());
            }
        });
    }
}
