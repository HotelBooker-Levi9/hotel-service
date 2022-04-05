package com.example.hotelservice.model.dto;


import com.example.hotelservice.model.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
	
	private Long id;
	private String name;
	private String imageUrl;
	private String description;
	private Integer pricePerDay;
	private Integer capacity;
	private boolean isDeleted;
	private CityDTO cityDTO;


}
