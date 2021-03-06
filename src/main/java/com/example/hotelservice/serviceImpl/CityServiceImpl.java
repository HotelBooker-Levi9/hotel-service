package com.example.hotelservice.serviceImpl;
import com.example.hotelservice.mapper.CityAdapter;
import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.repository.CityRepository;
import com.example.hotelservice.repository.DestinationRepository;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.CRUDService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Service
public class CityServiceImpl implements CRUDService<CityDTO> {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private HotelRepository hotelRepository;
    
    @Value("${reservations}")
    private String reservations;

	@Override
	public ResponseEntity<?> add(CityDTO cityDTO) {
		try {
			City res = CityAdapter.convertDto(cityDTO);
			res.setId(cityDTO.getId());
			res.setImageUrl(cityDTO.getImageUrl());
			res.setName(cityDTO.getName());
			res.setIsDeleted(cityDTO.getIsDeleted());
			res.setDestination(destinationRepository.findById(cityDTO.getDestinationDTO().getId()).get());
			cityRepository.save(res);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@Override
	public ResponseEntity<?> remove(Long id, boolean delete) {
		try {
			City city=cityRepository.findById(id).get();
			List<Hotel> hotelsForCity= hotelRepository.findAllHotelsForCity(id);
			List<Hotel> noReservation=new ArrayList<>();
			if(!hotelsForCity.isEmpty()) {
				for(Hotel hotel:hotelsForCity) {
					Date dateNow=new Date();
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					RestTemplate restTemplate = new RestTemplate();
					ResponseEntity<Boolean> reserved = restTemplate.getForEntity( reservations + simpleDateFormat.format(dateNow) +"/"+ hotel.getId(), Boolean.class);
					if(reserved.getBody().equals(true)) {
							noReservation.add(hotel);
					}
					else {
						 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}
				if(delete) {
					if(noReservation.equals(hotelsForCity)) {
						for(Hotel hotel:noReservation) {
							hotel.setIsDeleted(true);
							hotelRepository.save(hotel);
						}
						city.setIsDeleted(true);
					}
				}
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		try {
			return new ResponseEntity<CityDTO>(CityAdapter.convertToDTO(cityRepository.findById(id).get()),HttpStatus.OK);
		}catch(NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> findAll(){
		try {
			return new ResponseEntity<>(CityAdapter.convertListToDTO(cityRepository.findAll()),HttpStatus.OK);
		}catch(NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Transactional
	@Override
	public ResponseEntity<?> update(CityDTO cityDTO) {
		try {
			City res = CityAdapter.convertDto(cityDTO);
			City city = cityRepository.findById(res.getId()).get();
			city.setName(cityDTO.getName());
			city.setImageUrl(cityDTO.getImageUrl());

			city.setIsDeleted(cityDTO.getIsDeleted());
			city.setDestination(destinationRepository.findById(cityDTO.getDestinationDTO().getId()).get());
			cityRepository.save(city);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> remove(Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

   
}
