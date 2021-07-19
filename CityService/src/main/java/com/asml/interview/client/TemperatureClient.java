package com.asml.interview.client;

import com.asml.interview.model.TemperatureInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("TemperatureProvider")
public interface TemperatureClient {
    @GetMapping(value = "/v1/temperature/{city}",consumes = "application/json",produces = "application/json")
    TemperatureInformation getTemperatureByCity(@PathVariable("city") String city);
}
