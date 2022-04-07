package com.example.hotelservice.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.hotelservice.model.City;
import com.example.hotelservice.model.dto.CityDTO;

public interface CityService {
	 ResponseEntity<?> addCity(CityDTO cityDTO);
	 ResponseEntity<?> removeCity(Long id, boolean delete);
	  ResponseEntity<CityDTO> findOne(Long id);
	  ResponseEntity<?> updateCity(CityDTO cityDTO);
	  ResponseEntity<List<CityDTO>> findAll();
	  
}
