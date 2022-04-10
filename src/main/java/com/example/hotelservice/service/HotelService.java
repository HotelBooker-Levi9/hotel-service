package com.example.hotelservice.service;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.dto.SearchDTO;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Date;

public interface HotelService {
    ResponseEntity<?> getCapacityForHotelId(Long id);

    ResponseEntity<?> calculatePriceForReservation(ReservationDTO resDto);

    ResponseEntity<?> getAll();

    ResponseEntity<?> search(SearchDTO searchDto);

    ResponseEntity<?> searchParams(String hotelName, Integer pricePerDay,
                                   String cityName, String destinationName, String checkInDate, String checkOutDate, Integer guestNum) throws ParseException;

    ResponseEntity<?> top10();
}
