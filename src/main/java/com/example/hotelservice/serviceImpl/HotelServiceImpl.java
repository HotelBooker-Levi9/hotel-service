package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel getCapacityForHotelId(Long id) {
        return hotelRepository.findById(id).get();
    }
}
