package com.example.hotelservice.controller;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.dto.SearchDTO;
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

        @PostMapping("search")
        public ResponseEntity<?> search(@RequestBody SearchDTO searchDto) {
                return hotelService.search(searchDto);
        }

        @GetMapping("/top10")
        public ResponseEntity<?> top10() {
                return hotelService.top10();
        }
}
