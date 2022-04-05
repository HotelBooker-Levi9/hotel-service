package com.example.hotelservice.serviceImpl;


import com.example.hotelservice.mapper.DestinationAdapter;
import com.example.hotelservice.mapper.HotelAdapter;
import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.model.dto.DestinationDTO;
import com.example.hotelservice.repository.DestinationRepository;

import com.example.hotelservice.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;
    
	@Override
	public ResponseEntity<?> addDestination(DestinationDTO destinationDTO) {
		
			try {
					Destination res = DestinationAdapter.convertDtoTo(destinationDTO);
			       
			       res.setId(destinationDTO.getId());
			       res.setImageUrl(destinationDTO.getImageUrl());
			       res.setName(destinationDTO.getName());
			       
			        destinationRepository.save(res);
			        return new ResponseEntity<>(HttpStatus.CREATED);
			        
			}catch(Exception ex) {
				ex.getMessage();
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}

	@Override
	public ResponseEntity<?> updateDestination(DestinationDTO destinationDTO) {
		try {
			Destination res = DestinationAdapter.convertDtoTo(destinationDTO);
			Destination des=destinationRepository.findById(res.getId()).get();
			des.setImageUrl(destinationDTO.getImageUrl());
			des.setName(destinationDTO.getName());
	         destinationRepository.save(des);
	        return new ResponseEntity<>(HttpStatus.OK);

	}catch(Exception ex) {
		ex.getMessage();
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
		
	


}
