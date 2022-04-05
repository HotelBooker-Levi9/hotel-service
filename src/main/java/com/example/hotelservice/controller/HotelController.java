package com.example.hotelservice.controller;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.service.CityService;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

        @Autowired
        private HotelService hotelService;


        @GetMapping("/capacityForHotel/{id}")
        public ResponseEntity<?> getCapacityForHotelId(@PathVariable Long id) throws NullPointerException {
                try {
                        return new ResponseEntity<>(hotelService.getCapacityForHotelId(id).getCapacity(), HttpStatus.OK);
                } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                        return null;
                }
        }

        @PostMapping("/priceForReservation")
        public ResponseEntity<?> calculatePriceForReservation(@RequestBody ReservationDTO resDto) {
                return new ResponseEntity<>(hotelService.calculatePriceForReservation(resDto), HttpStatus.OK);
        }
}
