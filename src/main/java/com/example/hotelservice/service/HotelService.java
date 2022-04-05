package com.example.hotelservice.service;


import org.springframework.http.ResponseEntity;


import com.example.hotelservice.model.dto.HotelDTO;

public interface HotelService {
	 ResponseEntity<?> addHotel(HotelDTO hotelDTO);
	 ResponseEntity<?> updateHotel(HotelDTO hotelDTO);
}
