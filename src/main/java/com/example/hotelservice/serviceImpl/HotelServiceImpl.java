package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public ResponseEntity<?> getCapacityForHotelId(Long id) {
        try {
            return new ResponseEntity<>(hotelRepository.findById(id).get().getCapacity(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> calculatePriceForReservation(ReservationDTO resDto) {
        Long diffInMillies = Math.abs(resDto.getCheckOutDate().getTime() - resDto.getCheckInDate().getTime());
        Long numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        try {
            Hotel hotel = hotelRepository.findById(resDto.getHotelId()).get();
            return new ResponseEntity<>(Math.toIntExact(numberOfDays) * hotel.getPricePerDay() * resDto.getGuestNumber(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
