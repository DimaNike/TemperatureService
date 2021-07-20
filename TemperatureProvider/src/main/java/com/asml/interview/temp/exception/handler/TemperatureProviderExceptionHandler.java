package com.asml.interview.temp.exception.handler;

import com.asml.interview.temp.exception.CityNotFoundException;
import com.asml.interview.temp.model.TemperatureInformation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TemperatureProviderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CityNotFoundException.class})
    public ResponseEntity<TemperatureInformation> handleCityNotFoundCase(CityNotFoundException exception) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Description", exception.getMessage());
        return ResponseEntity.notFound()
                .headers(responseHeaders)
                .build();
    }
}
