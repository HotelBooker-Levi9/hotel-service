package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel getCapacityForHotelId(Long id) throws NoSuchElementException {
        try {
            return hotelRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Integer calculatePriceForReservation(ReservationDTO resDto) throws NoSuchElementException {
        Long diffInMillies = Math.abs(resDto.getCheckOutDate().getTime() - resDto.getCheckInDate().getTime());
        Long numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        try {
            Hotel hotel = hotelRepository.findById(resDto.getHotelId()).get();
            return Math.toIntExact(numberOfDays) * hotel.getPricePerDay() * resDto.getGuestNumber();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
