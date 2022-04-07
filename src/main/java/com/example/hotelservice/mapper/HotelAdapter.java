package com.example.hotelservice.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.model.dto.CityDTO;
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
	        res.setCity(CityAdapter.convertDto(dto.getCityDTO()));
	        return res;
	    }
	 public static HotelDTO convertToDTO(Hotel hotel) {
	        HotelDTO res = new HotelDTO();

	        res.setId(hotel.getId());
	        res.setName(hotel.getName());
	        res.setImageUrl(hotel.getImageUrl());
	        res.setDescription(hotel.getDescription());
	        res.setPricePerDay(hotel.getPricePerDay());
	        res.setCapacity(hotel.getCapacity());
	        res.setDeleted(hotel.getIsDeleted());
	        res.setCityDTO(CityAdapter.convertToDTO(hotel.getCity()));
	        return res;
	    }
	 public static List<HotelDTO> convertListToDTO(List<Hotel> hotels) {
			List<HotelDTO> dtos = new ArrayList<>();
			for (Hotel hotel : hotels)
				dtos.add(convertToDTO(hotel));

			return dtos;
	    }
}
