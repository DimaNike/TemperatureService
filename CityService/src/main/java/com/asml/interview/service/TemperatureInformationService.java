package com.asml.interview.service;

import com.asml.interview.client.TemperatureClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TemperatureInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureInformationService.class);

    private TemperatureClient temperatureClient;

    public TemperatureInformationService(TemperatureClient temperatureClient) {
        this.temperatureClient = temperatureClient;
    }

    @Scheduled(cron = "${city.service.cron}")
    public void getCityTemperature() {
        LOGGER.info("WORKS");
        LOGGER.info("result: {}", temperatureClient.getTemperatureByCity("blabla").getTemperature());
    }
}
