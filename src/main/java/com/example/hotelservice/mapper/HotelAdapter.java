package com.example.hotelservice.mapper;

import com.example.hotelservice.model.Hotel;

import com.example.hotelservice.model.dto.HotelDTO;

public class HotelAdapter {
	 public static Hotel convertDto(HotelDTO dto) {
	        Hotel res = new Hotel();

	        res.setId(dto.getId());
	        res.setName(dto.getName());
	        res.setImageUrl(dto.getImageUrl());
	        res.setDescription(dto.getDescription());
	        res.setPricePerDay(dto.getPricePerDay());
	        res.setCapacity(dto.getCapacity());
	        res.setIsDeleted(dto.isDeleted());
	        return res;
	    }
}
