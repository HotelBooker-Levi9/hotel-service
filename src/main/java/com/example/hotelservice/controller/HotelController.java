package com.example.hotelservice.controller;



import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.model.dto.DestinationDTO;
import com.example.hotelservice.model.dto.HotelDTO;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.DestinationService;
import com.example.hotelservice.service.HotelService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {
	
        @Autowired
        private HotelService hotelService;
        
  
        @PostMapping("/addHotel")
        public ResponseEntity<?> addHotel(@RequestBody HotelDTO hotelDTO) {
        	hotelService.addHotel(hotelDTO);
        	return new ResponseEntity<>(HttpStatus.OK);
        	
        }
        @PutMapping
        public ResponseEntity<Void> updateHotel(@RequestBody HotelDTO hotelDTO) {
        		
              hotelService.updateHotel(hotelDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        @PutMapping(value="/{hotelId}")
        public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        	
              hotelService.removeHotel(hotelId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
       	public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
       		
       		return hotelService.findOne(id);
       	}

           @RequestMapping(value = "/", method = RequestMethod.GET)
       	public ResponseEntity<List<HotelDTO>> getAllHotels() {
       		
       		return hotelService.findAll();
       	}
}
