package com.example.hotelservice.controller;

import com.example.hotelservice.service.CityService;
import com.example.hotelservice.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cities")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CityController {

    @Autowired
    private CityService cityService;


}