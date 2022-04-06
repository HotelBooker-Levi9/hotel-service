package com.example.hotelservice.service;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.dto.SearchDTO;
import org.springframework.http.ResponseEntity;

public interface HotelService {
    ResponseEntity<?> getCapacityForHotelId(Long id);

    ResponseEntity<?> calculatePriceForReservation(ReservationDTO resDto);

    ResponseEntity<?> getAll();

    ResponseEntity<?> search(SearchDTO searchDto);

    ResponseEntity<?> top10();
}
