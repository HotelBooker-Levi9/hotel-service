package com.example.hotelservice.controller;

import com.example.hotelservice.model.dto.CityDTO;

import com.example.hotelservice.serviceImpl.CityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cities")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CityController {

	@Autowired
	private CityServiceImpl cityService;

	@GetMapping("/")
	public ResponseEntity<?> getAllCities() {
		return cityService.findAll();
	}

	@PostMapping("/addCity")
	public ResponseEntity<?> addCity(@RequestBody CityDTO cityDTO) {
		return cityService.add(cityDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable Long id) {
		return cityService.remove(id, true);
	}

	@PutMapping
	public ResponseEntity<?> updateCity(@RequestBody CityDTO cityDTO) {
		return cityService.update(cityDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCityById(@PathVariable Long id) {
		return cityService.findOne(id);
	}



}