package com.example.hotelservice.mapper;


import com.example.hotelservice.model.City;
import com.example.hotelservice.model.dto.CityDTO;

public class CityAdapter {
	
	 public static City convertDto(CityDTO dto) {
	        City res = new City();

	        res.setId(dto.getId());
	        res.setName(dto.getName());
	        res.setImageUrl(dto.getImageUrl());
	        
	        return res;
	    }

}
