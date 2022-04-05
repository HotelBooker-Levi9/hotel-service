package com.example.hotelservice.controller;


import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.service.CityService;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;
    

    @PostMapping("/addCity")
    public void addCity(@RequestBody CityDTO cityDTO) {
          cityService.addCity(cityDTO);
        
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
    	
          cityService.removeCity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Void> updateCity(@RequestBody CityDTO cityDTO) {
    	
          cityService.updateCity(cityDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}