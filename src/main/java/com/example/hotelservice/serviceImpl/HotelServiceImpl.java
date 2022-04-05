package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.mapper.CityAdapter;
import com.example.hotelservice.mapper.HotelAdapter;
import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.model.dto.DestinationDTO;
import com.example.hotelservice.model.dto.HotelDTO;
import com.example.hotelservice.repository.CityRepository;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.CityService;
import com.example.hotelservice.service.HotelService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CityRepository cityRepository;

	@Override
	public ResponseEntity<?> addHotel(HotelDTO hotelDTO) {
		try {
			Hotel res = HotelAdapter.convertDto(hotelDTO);
		       
		       res.setId(hotelDTO.getId());
		       res.setImageUrl(hotelDTO.getImageUrl());
		       res.setName(hotelDTO.getName());
		       res.setDescription(hotelDTO.getDescription());
		       res.setPricePerDay(hotelDTO.getPricePerDay());
		       res.setCapacity(hotelDTO.getCapacity());
		       res.setIsDeleted(hotelDTO.isDeleted());
		       res.setCity(cityRepository.findById(hotelDTO.getCityDTO().getId()).get());
		        hotelRepository.save(res);
		        return new ResponseEntity<>(HttpStatus.CREATED);
			
		}catch(Exception ex) {
			ex.getMessage();
		}
		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}

	@Override
	public ResponseEntity<?> updateHotel(HotelDTO hotelDTO) {
		try {
				Hotel res = HotelAdapter.convertDto(hotelDTO);
				Hotel hotel=hotelRepository.findById(res.getId()).get();
				hotel.setName(hotelDTO.getName());
		       hotel.setImageUrl(hotelDTO.getImageUrl());
		       hotel.setDescription(hotelDTO.getDescription());
		       hotel.setPricePerDay(hotelDTO.getPricePerDay());
		       hotel.setCapacity(hotelDTO.getCapacity());
		       hotel.setIsDeleted(hotelDTO.isDeleted());
		       hotel.setCity(cityRepository.findById(hotelDTO.getCityDTO().getId()).get());
		        hotelRepository.save(hotel);
		        return new ResponseEntity<>(HttpStatus.OK);
	
		}catch(Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
}
