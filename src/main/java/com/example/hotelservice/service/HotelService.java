package com.example.hotelservice.service;


import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.model.dto.HotelDTO;

public interface HotelService {
	 ResponseEntity<?> addHotel(HotelDTO hotelDTO);
	 ResponseEntity<?> updateHotel(HotelDTO hotelDTO);
	 ResponseEntity<?> removeHotel(Long hotelId);
	 ResponseEntity<?> findOne(Long hotelId);
	 ResponseEntity<?> findAll();
	 
}
