package com.example.hotelservice.service;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.model.Hotel;

public interface HotelService {
    Hotel getCapacityForHotelId(Long id);

    Integer calculatePriceForReservation(ReservationDTO resDto);
}
