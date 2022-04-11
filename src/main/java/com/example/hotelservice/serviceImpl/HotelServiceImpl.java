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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class HotelServiceImpl implements CRUDService<HotelDTO> {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CityRepository cityRepository;
    public static final String RESERVATIONS="http://localhost:8765/reservations/reservationInFuture/";

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
					.getForEntity(RESERVATIONS+ simpleDateFormat.format(dateNow) + "/" + hotelId, Boolean.class);

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
		return new ResponseEntity<>(HttpStatus.CONFLICT);
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

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;

	}

	@Override
	public ResponseEntity<?> remove(Long id, boolean deleted) {
		// TODO Auto-generated method stub
		return null;
	}
}
