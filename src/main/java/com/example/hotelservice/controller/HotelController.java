package com.example.hotelservice.controller;

import com.example.hotelservice.model.dto.HotelDTO;

import com.example.hotelservice.serviceImpl.HotelServiceImpl;

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
import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping(value = "/hotels")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class HotelController {

	@Autowired
	private HotelServiceImpl hotelService;

	@PostMapping("/addHotel")
	public ResponseEntity<?> addHotel(@RequestBody HotelDTO hotelDTO) {
		return hotelService.add(hotelDTO);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateHotel(@RequestBody HotelDTO hotelDTO) {
		return hotelService.update(hotelDTO);
	}

	@PutMapping(value = "/{hotelId}")
	public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId) {
		return hotelService.remove(hotelId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getHotelById(@PathVariable Long id) {
		return hotelService.findOne(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllHotels() {
		return hotelService.findAll();
	}
	
    @GetMapping("/capacityForHotel/{id}")
    public ResponseEntity<?> getCapacityForHotelId(@PathVariable Long id) {
        return hotelService.getCapacityForHotelId(id);
    }

    @PostMapping("/priceForReservation")
    public ResponseEntity<?> calculatePriceForReservation(@RequestBody ReservationDTO resDto) {
        return hotelService.calculatePriceForReservation(resDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return hotelService.getAll();
    }

    //POST??
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchDTO searchDto) {
        return hotelService.search(searchDto);
    }

    @GetMapping("/searchParams")
    public ResponseEntity<?> searchParams(@RequestParam(name = "hotelName") String hotelName,
                                          @RequestParam(name = "pricePerDay") Integer pricePerDay,
                                          @RequestParam(name = "cityName") String cityName,
                                          @RequestParam(name = "destinationName") String destinationName,
                                          @RequestParam(name = "checkInDate") String checkInDate,
                                          @RequestParam(name = "checkOutDate") String checkOutDate,
                                          @RequestParam(name = "guestNum") Integer guestNum) throws ParseException {
        return hotelService.searchParams(hotelName, pricePerDay, cityName, destinationName, checkInDate,
                                checkOutDate, guestNum);
    }

    @GetMapping("/top10")
    public ResponseEntity<?> top10() {
        return hotelService.top10();
    }

    @GetMapping("/hotelInfo/{id}")
    public ResponseEntity<?> hotelInfo(@PathVariable Long id) {
        return hotelService.hotelInfo(id);
    }
        
}
