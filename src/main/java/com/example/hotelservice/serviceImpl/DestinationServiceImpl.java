package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.mapper.DestinationAdapter;
import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.dto.DestinationDTO;
import com.example.hotelservice.repository.DestinationRepository;

import com.example.hotelservice.service.CRUDService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DestinationServiceImpl implements CRUDService<DestinationDTO> {

    @Autowired
    private DestinationRepository destinationRepository;
   
    @Autowired 
    private CityServiceImpl cityService;

	@Override
	@Transactional
	public ResponseEntity<?> add(DestinationDTO destinationDTO) {

		try {
			Destination res = DestinationAdapter.convertDtoTo(destinationDTO);

			res.setId(destinationDTO.getId());
			res.setImageUrl(destinationDTO.getImageUrl());
			res.setName(destinationDTO.getName());
			res.setIsDeleted(destinationDTO.getIsDeleted());

			destinationRepository.save(res);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@Override
	@Transactional
	public ResponseEntity<?> update(DestinationDTO destinationDTO) {
		try {
			Destination res = DestinationAdapter.convertDtoTo(destinationDTO);
			Destination des = destinationRepository.findById(res.getId()).get();
			des.setImageUrl(destinationDTO.getImageUrl());
			des.setName(destinationDTO.getName());
			des.setIsDeleted(destinationDTO.getIsDeleted());
			destinationRepository.save(des);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		try {
			DestinationDTO destinationDTO = DestinationAdapter.convertToDTO(destinationRepository.findById(id).get());
			return new ResponseEntity<>(destinationDTO, HttpStatus.OK);
		} catch (NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<?> findAll() {
		try {
			List<DestinationDTO> destinations = DestinationAdapter.convertListToDTO(destinationRepository.findAll());

			return new ResponseEntity<>(destinations,HttpStatus.OK);
		} catch (NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	@Transactional
	@Override
	public ResponseEntity<?> remove(Long id) {
		try {
			Destination destination = destinationRepository.findById(id).get();
			List<City> cities = destinationRepository.findAllCitiesForDestination(id);
			List<City> noReservation = new ArrayList<>();

			if (!cities.isEmpty()) {
				for (City city : cities) {
					if (cityService.remove(city.getId(), false).getStatusCode() == HttpStatus.OK) {

						noReservation.add(city);
					}
				}
				if (noReservation.equals(cities)) {
					for (City city : cities) {
						cityService.remove(city.getId(), true);
					}
					destination.setIsDeleted(true);

					return new ResponseEntity<>(HttpStatus.OK);
				}

				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			destination.setIsDeleted(true);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<?> remove(Long id, boolean deleted) {
		// TODO Auto-generated method stub
		return null;
	}

}
