package com.example.hotelservice.model.dto;

import java.util.Set;

import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
	
	private Long id;
	private String name;
	private String imageUrl;
	private Boolean isDeleted;
	private DestinationDTO destinationDTO;
	

	
	
	
}
