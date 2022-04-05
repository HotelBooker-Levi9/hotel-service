package com.example.hotelservice.serviceImpl;



import com.example.hotelservice.mapper.CityAdapter;
import com.example.hotelservice.mapper.DestinationAdapter;
import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.repository.CityRepository;
import com.example.hotelservice.repository.DestinationRepository;
import com.example.hotelservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DestinationRepository destinationRepository;
	@Override
	public ResponseEntity<?> addCity(CityDTO cityDTO) {
		try {
			City res = CityAdapter.convertDto(cityDTO);
		       
		       res.setId(cityDTO.getId());
		       res.setImageUrl(cityDTO.getImageUrl());
		       res.setName(cityDTO.getName());
		       
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
	public ResponseEntity<?> removeCity(Long id) {
		try {
		City city=cityRepository.findById(id).get();
		if(city!=null) {
			city.setDeleted(true);
			new ResponseEntity<>(HttpStatus.OK);
			
		}
		}
		catch(Exception ex) {
			ex.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	@Override
	public City findOne(Long id) {
		try {
			City city=cityRepository.findById(id).get();
			return city;
		}catch(Exception ex) {
			ex.getMessage();
		}
		return null;
		
	}
	@Override
	public ResponseEntity<?> updateCity(CityDTO cityDTO) {
		try {
			City res = CityAdapter.convertDto(cityDTO);
			City city=cityRepository.findById(res.getId()).get();
			city.setImageUrl(cityDTO.getImageUrl());
			city.setName(cityDTO.getName());
			city.setDeleted(cityDTO.isDeleted());
			city.setDestination(destinationRepository.findById(cityDTO.getDestinationDTO().getId()).get());
	         cityRepository.save(city);
	        return new ResponseEntity<>(HttpStatus.OK);

	}catch(Exception ex) {
		ex.getMessage();
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
		
	
   
}
