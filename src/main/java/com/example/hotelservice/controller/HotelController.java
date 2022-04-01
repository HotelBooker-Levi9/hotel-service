package com.example.hotelservice.controller;

import com.example.hotelservice.service.CityService;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

        @Autowired
        private HotelService hotelService;

}
