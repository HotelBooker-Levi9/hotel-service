package com.example.hotelservice.controller;

import com.example.hotelservice.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/destinations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;


}
