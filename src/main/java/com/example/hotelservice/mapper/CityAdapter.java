package com.example.hotelservice.mapper;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hotelservice.model.City;
import com.example.hotelservice.model.dto.CityDTO;



public class CityAdapter {
	
	 public static City convertDto(CityDTO dto) {
	        City res = new City();

	        res.setId(dto.getId());
	        res.setName(dto.getName());
	        res.setImageUrl(dto.getImageUrl());
	        res.setDeleted(dto.isDeleted());
	        res.setDestination(DestinationAdapter.convertDtoTo(dto.getDestinationDTO()));
	        return res;
	    }
	 static public CityDTO convertToDTO(City city) {
		 CityDTO cityDTO=new CityDTO();
		 cityDTO.setId(city.getId());
		 cityDTO.setName(city.getName());
		 cityDTO.setImageUrl(city.getImageUrl());
		 cityDTO.setDeleted(city.isDeleted());
		 cityDTO.setDestinationDTO(DestinationAdapter.convertToDTO(city.getDestination()));
			return cityDTO;
			
		}
	 public static List<CityDTO> convertListToDTO(List<City> cities) {
			List<CityDTO> dtos = new ArrayList<>();
			for (City city : cities)
				dtos.add(convertToDTO(city));

			return dtos;
	    }
}
