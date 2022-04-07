package com.example.hotelservice.service;


import java.util.List;

import org.springframework.http.ResponseEntity;


import com.example.hotelservice.model.dto.DestinationDTO;

public interface DestinationService {
	
	
	ResponseEntity<?> addDestination(DestinationDTO destinationDTO);
	ResponseEntity<?> updateDestination(DestinationDTO destinationDTO);
	ResponseEntity<DestinationDTO> findOne(Long id);
	ResponseEntity<List<DestinationDTO>> findAll();
	ResponseEntity<?> removeDestination(Long id);
	

}
