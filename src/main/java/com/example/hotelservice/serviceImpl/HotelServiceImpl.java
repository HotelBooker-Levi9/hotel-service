package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.dto.*;
import com.example.hotelservice.mapper.ArrangementAdapter;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class HotelServiceImpl implements HotelService {
    public static final String GATEWAY_URL = "http://localhost:8765/";

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

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(ArrangementAdapter.convertHotelListToArrangementList(hotelRepository.findAllByIsDeleted(false)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> search(SearchDTO searchDto) {
        List<Long> unavailableHotels = Arrays.asList(getUnavailableHotelIdsForDateRange(searchDto.getCheckInDate(), searchDto.getCheckOutDate(), searchDto.getGuestNum()));
        List<Arrangement> arrangements;
        try {
            if (unavailableHotels.isEmpty())
                arrangements = ArrangementAdapter.convertHotelListToArrangementList(hotelRepository.findSearchResultsWhenDontHaveUnavailable(
                        searchDto.getHotelName(), searchDto.getPricePerDay(), searchDto.getCityName(), searchDto.getDestinationName(), searchDto.getGuestNum()));
            else
                arrangements = ArrangementAdapter.convertHotelListToArrangementList(hotelRepository.findSearchResults(
                        searchDto.getHotelName(), searchDto.getPricePerDay(), searchDto.getCityName(), searchDto.getDestinationName(), unavailableHotels, searchDto.getGuestNum()));

            return new ResponseEntity<>(arrangements, HttpStatus.FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> top10() {
        List<Arrangement> top10 = new ArrayList<>();
        Integer i = 0;
        for (HotelReervationsCount hotelCount : getTop10VisitedHotels().getBody()) {
            top10.add(getArrangementForHotel(hotelCount.getHotelId()));
            if(++i > 9)
                break;
        }
        return new ResponseEntity<>(top10, HttpStatus.OK);
    }

    private Arrangement getArrangementForHotel(Long hotelId) {
        return ArrangementAdapter.convertHotelToArrangement(hotelRepository.findById(hotelId).get());
    }

    private ResponseEntity<HotelReervationsCount[]> getTop10VisitedHotels() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(GATEWAY_URL + "reservations/top10hotels", HotelReervationsCount[].class);
    }

    private Long[] getUnavailableHotelIdsForDateRange(Date checkInDate, Date checkOutDate, Integer guestNum) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(GATEWAY_URL + "carts/unavailableHotelIdsForDateRange", new DateRangeWithGuestNum(checkInDate, checkOutDate, guestNum), Long[].class);
    }
}
