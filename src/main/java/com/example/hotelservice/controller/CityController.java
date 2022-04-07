package com.example.hotelservice.controller;


import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.service.CityService;


import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;
    

    @PostMapping("/addCity")
    public void addCity(@RequestBody CityDTO cityDTO) {
    	System.out.println("City dtooo add"+cityDTO);
          cityService.addCity(cityDTO);
        
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
    	
          cityService.removeCity(id,true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Void> updateCity(@RequestBody CityDTO cityDTO) {
    	
          cityService.updateCity(cityDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
		
		return cityService.findOne(id);
	}

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> getAllCities() {
		
		return cityService.findAll();
	}

}