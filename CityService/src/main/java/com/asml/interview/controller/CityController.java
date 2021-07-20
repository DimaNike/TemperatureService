package com.asml.interview.controller;

import com.asml.interview.dto.City;
import com.asml.interview.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/addCityForm")
    public String showAddCityForm(City city) {
        return "add-city";
    }

    @PostMapping("/addCity")
    public String addCity(@Valid City city) {
        cityService.addCity(city);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable("id") long id) {
        cityService.removeCity(id);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "index";
    }

}
