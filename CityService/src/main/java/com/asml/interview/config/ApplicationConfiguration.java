package com.asml.interview.config;

import com.asml.interview.service.TemperatureInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Value("${city.service.cron.rate}")
    private Long cronJobRate;
    private final static Long DEFAULT_RATE = 10000L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    public Long getCronJobRate() {
        if (cronJobRate > 5 && cronJobRate < 60) {
            return cronJobRate * 1000;
        } else {
            LOGGER.warn("Cron job rate has incorrect value or was not setup: {}; Using default value: {}", cronJobRate, DEFAULT_RATE);
            return DEFAULT_RATE;
        }
    }
}
