package com.example.hotelservice.service;

import com.example.hotelservice.dto.ReservationDTO;
import org.springframework.http.ResponseEntity;

public interface HotelService {
    ResponseEntity<?> getCapacityForHotelId(Long id);

    ResponseEntity<?> calculatePriceForReservation(ReservationDTO resDto);
}
