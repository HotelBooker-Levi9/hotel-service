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
				destination.setDeleted(true);

				return new ResponseEntity<>(HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		destination.setDeleted(true);

		return new ResponseEntity<>(HttpStatus.OK);
//		for(City city:cities) {
//			hotelsForCities.addAll(hotelRepository.findAllHotelsForCity(city.getId()));
//		
//		List<Hotel> noReservation=new ArrayList<>();
//		if(!hotelsForCities.isEmpty()) {
//			for(Hotel hotel:hotelsForCities) {
//				Date dateNow=new Date();
//	        	String pattern = "yyyy-MM-dd";
//	        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//	            RestTemplate restTemplate = new RestTemplate();
//	            ResponseEntity<Boolean> reserved = restTemplate.getForEntity( "http://localhost:8100/reservations/reservationInFuture/" + simpleDateFormat.format(dateNow) +"/"+ hotel.getId(), Boolean.class);
//	            if(reserved.getBody().equals(true)) {
//	            		
//	    				noReservation.add(hotel);
//    		
//	            }
//	            else {
//	            	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	            }
//			}
//			if(noReservation.equals(hotelsForCities)) {
//				for(Hotel hotel:noReservation) {
//					
//					hotel.setIsDeleted(true);
//					hotelRepository.save(hotel);
//				
//			}
//				city.setDeleted(true);
//				
//				new ResponseEntity<>(HttpStatus.OK);
//				
//			
//		}
//				
//		}
//		else {
//       	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//       }
//		}
		}
		catch(Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	


}
