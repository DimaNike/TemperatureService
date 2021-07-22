package com.asml.interview.client;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockedService {

    @GetMapping(value = "/v1/temperature/{city}", produces = "application/json")
    public ResponseEntity<String> getTemperatureByCity(@PathVariable("city") String cityName) {
        if (cityName.equals("Eindhoven")) {
            return ResponseEntity.ok("{\"time\":\"2019-10-12T07:20:50.52Z\",\"temperature\":\"40.585\"}");
        }
        if (cityName.equals("Venlo")) {
            return ResponseEntity.notFound().build();
        }
        if (cityName.equals("500")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
