package com.example.hotelservice.service;


import java.util.List;

import org.springframework.http.ResponseEntity;


import com.example.hotelservice.model.dto.DestinationDTO;

public interface DestinationService {
	
	
	ResponseEntity<?> addDestination(DestinationDTO destinationDTO);
	ResponseEntity<?> updateDestination(DestinationDTO destinationDTO);
	ResponseEntity<?> findOne(Long id);
	ResponseEntity<?> findAll();
	ResponseEntity<?> removeDestination(Long id);
	

}
