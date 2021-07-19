package com.asml.interview.temp.controller;

import com.asml.interview.temp.model.TemperatureInformation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TemperatureController {

    @GetMapping(value = "/v1/temperature/{city}")
    public ResponseEntity<TemperatureInformation> getTemperatureByCity(@PathVariable("city") String cityName) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Description",
                "Temperature in the Netherlands");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new TemperatureInformation(LocalDateTime.now(), 16.22));
    }

}
