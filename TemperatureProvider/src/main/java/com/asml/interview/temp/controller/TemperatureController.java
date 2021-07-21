package com.asml.interview.temp.controller;

import com.asml.interview.temp.model.TemperatureInformation;
import com.asml.interview.temp.service.TemperatureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TemperatureController {
    public static final String RESPONSE_200 = "Temperature in the Netherlands";
    public static final String RESPONSE_404 = "Temperature for %s could not be found";

    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping(value = "/v1/temperature/{city}")
    public ResponseEntity<TemperatureInformation> getTemperatureByCity(@PathVariable("city") String cityName) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Description", RESPONSE_200);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(temperatureService.getTemperatureByCityName(cityName));
    }

}
