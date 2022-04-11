package com.example.hotelservice.serviceImpl;



import com.example.hotelservice.mapper.HotelAdapter;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.model.dto.HotelDTO;
import com.example.hotelservice.repository.CityRepository;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.CRUDService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import com.example.hotelservice.dto.*;
import com.example.hotelservice.mapper.ArrangementAdapter;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class HotelServiceImpl implements CRUDService<HotelDTO> {
    public static final String GATEWAY_URL = "http://localhost:8765/";

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CityRepository cityRepository;
    
    @Value("${reservations}")
    private String reservations;
   
	@Override
	@Transactional
	public ResponseEntity<?> add(HotelDTO hotelDTO) {
		try {
			Hotel res = HotelAdapter.convertDto(hotelDTO);

			res.setId(hotelDTO.getId());
			res.setImageUrl(hotelDTO.getImageUrl());
			res.setName(hotelDTO.getName());
			res.setDescription(hotelDTO.getDescription());
			res.setPricePerDay(hotelDTO.getPricePerDay());
			res.setCapacity(hotelDTO.getCapacity());
			res.setIsDeleted(hotelDTO.getIsDeleted());
			res.setCity(cityRepository.findById(hotelDTO.getCityDTO().getId()).get());
			hotelRepository.save(res);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@Override
	@Transactional
	public ResponseEntity<?> update(HotelDTO hotelDTO) {
		try {
			Hotel res = HotelAdapter.convertDto(hotelDTO);
			Hotel hotel = hotelRepository.findById(res.getId()).get();
			hotel.setName(hotelDTO.getName());
			hotel.setImageUrl(hotelDTO.getImageUrl());
			hotel.setDescription(hotelDTO.getDescription());
			hotel.setPricePerDay(hotelDTO.getPricePerDay());
			hotel.setCapacity(hotelDTO.getCapacity());
			hotel.setIsDeleted(hotelDTO.getIsDeleted());
			hotel.setCity(cityRepository.findById(hotelDTO.getCityDTO().getId()).get());
			hotelRepository.save(hotel);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Transactional
	@Override
	public ResponseEntity<?> remove(Long hotelId) {
		try {
			Hotel hotel = hotelRepository.findById(hotelId).get();

			Date dateNow = new Date();
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Boolean> reserved = restTemplate
					.getForEntity(reservations+ simpleDateFormat.format(dateNow) + "/" + hotelId, Boolean.class);

			if (reserved.getBody().equals(true)) {

				if (hotel != null) {
					hotel.setIsDeleted(true);
					new ResponseEntity<>(HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<?> findOne(Long id) {
		try {
			HotelDTO hotelDTO = HotelAdapter.convertToDTO(hotelRepository.findById(id).get());
			return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
		} catch (NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<?> findAll() {
		try {
			List<HotelDTO> hotels = HotelAdapter.convertListToDTO(hotelRepository.findAll());

			return new ResponseEntity<>(hotels,HttpStatus.OK);
		} catch (NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;

	}

	@Override
	public ResponseEntity<?> remove(Long id, boolean deleted) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
    
    public ResponseEntity<?> getCapacityForHotelId(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent())
            return new ResponseEntity<>(hotel.get().getCapacity(), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    
    public ResponseEntity<?> calculatePriceForReservation(ReservationDTO resDto) {
        Long diffInMillies = Math.abs(resDto.getCheckOutDate().getTime() - resDto.getCheckInDate().getTime());
        Long numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        Optional<Hotel> hotel = hotelRepository.findById(resDto.getHotelId());
        if(hotel.isPresent()) {
            Integer calculatedPrice = Math.toIntExact(numberOfDays) * hotel.get().getPricePerDay() * resDto.getGuestNumber();
            return new ResponseEntity<>(calculatedPrice, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(ArrangementAdapter.convertHotelListToArrangementList(hotelRepository.findAllByIsDeleted(false)), HttpStatus.OK);
    }

    
    public ResponseEntity<?> search(SearchDTO searchDto) {
        List<Long> unavailableHotels = Arrays.asList(getUnavailableHotelIdsForDateRange(searchDto.getCheckInDate(), searchDto.getCheckOutDate(), searchDto.getGuestNum()));
        List<Arrangement> arrangements;

        if (unavailableHotels.isEmpty())
            arrangements = ArrangementAdapter.convertHotelListToArrangementList(hotelRepository.findSearchResultsWhenDontHaveUnavailable(
                    searchDto.getHotelName(), searchDto.getPricePerDay(), searchDto.getCityName(), searchDto.getDestinationName(), searchDto.getGuestNum()));
        else
            arrangements = ArrangementAdapter.convertHotelListToArrangementList(hotelRepository.findSearchResults(
                    searchDto.getHotelName(), searchDto.getPricePerDay(), searchDto.getCityName(), searchDto.getDestinationName(), unavailableHotels, searchDto.getGuestNum()));

        return new ResponseEntity<>(arrangements, HttpStatus.FOUND);
    }

    
    public ResponseEntity<?> top10() {
        List<Arrangement> top10 = new ArrayList<>();
        int i = 0;
        try {
            for (HotelReervationsCount hotelCount : Objects.requireNonNull(getTop10VisitedHotels().getBody())) {
                top10.add(getArrangementForHotel(hotelCount.getHotelId()));
                if (++i > 9)
                    break;
            }
            return new ResponseEntity<>(top10, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
