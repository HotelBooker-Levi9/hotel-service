package com.example.hotelservice.controller;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.dto.SearchDTO;
import com.example.hotelservice.service.CityService;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping(value = "/hotels")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelController {

        @Autowired
        private HotelService hotelService;


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
        
}
