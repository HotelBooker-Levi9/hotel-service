package com.example.hotelservice.serviceImpl;


import com.example.hotelservice.mapper.CityAdapter;
import com.example.hotelservice.mapper.DestinationAdapter;
import com.example.hotelservice.mapper.HotelAdapter;
import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.model.dto.DestinationDTO;
import com.example.hotelservice.repository.DestinationRepository;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.CityService;
import com.example.hotelservice.service.DestinationService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired 
    private CityService cityService;
    
	@Override
	public ResponseEntity<?> addDestination(DestinationDTO destinationDTO) {
		
			try {
					Destination res = DestinationAdapter.convertDtoTo(destinationDTO);
			       
			       res.setId(destinationDTO.getId());
			       res.setImageUrl(destinationDTO.getImageUrl());
			       res.setName(destinationDTO.getName());
			       res.setIsDeleted(destinationDTO.getIsDeleted());
			       
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
			des.setIsDeleted(destinationDTO.getIsDeleted());
	         destinationRepository.save(des);
	        return new ResponseEntity<>(HttpStatus.OK);

	}catch(Exception ex) {
		ex.getMessage();
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	@Override
	public ResponseEntity<DestinationDTO> findOne(Long id) {
		try {
			DestinationDTO destinationDTO=DestinationAdapter.convertToDTO(destinationRepository.findById(id).get());
			return new ResponseEntity<DestinationDTO>(destinationDTO,HttpStatus.OK);
		}catch(NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
		
	}
	@Override
	public ResponseEntity<List<DestinationDTO>> findAll(){
		try {
			List<DestinationDTO> destinations=DestinationAdapter.convertListToDTO(destinationRepository.findAll());
			
			return new ResponseEntity<List<DestinationDTO>>(destinations,HttpStatus.OK);
		}catch(NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
		
		
	}
	
	@Transactional
	@Override
	public ResponseEntity<?> removeDestination(Long id) {
		try {
		Destination destination=destinationRepository.findById(id).get();
		List<City> cities=destinationRepository.findAllCitiesForDestination(id);
		List<City> noReservation=new ArrayList<>();
		
		if(!cities.isEmpty()) {
			for(City city:cities) {
				if(cityService.removeCity(city.getId(), false).getStatusCode() == HttpStatus.OK) {
		
					
					noReservation.add(city);
				}
			}
			if(noReservation.equals(cities)) {
				for(City city:cities) {
					cityService.removeCity(city.getId(), true);	
				}
				destination.setIsDeleted(true);

				return new ResponseEntity<>(HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		destination.setIsDeleted(true);

		return new ResponseEntity<>(HttpStatus.OK);

		}
		catch(Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	


}
