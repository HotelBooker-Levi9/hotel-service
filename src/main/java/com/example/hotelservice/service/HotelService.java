package com.example.hotelservice.service;

import com.example.hotelservice.model.Hotel;

public interface HotelService {
    Hotel getCapacityForHotelId(Long id);
}
