package com.asml.interview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TemperatureInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureInformationService.class);
    @Scheduled(cron = "${city.service.cron}")
    public void getCityTemperature(){
        LOGGER.info("WORKS");
    }
}
