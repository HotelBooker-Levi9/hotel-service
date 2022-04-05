package com.example.hotelservice.model.dto;

import java.util.Set;

import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class DestinationDTO {
	
	private Long id;
	private String name;
	private String imageUrl;


}
