package com.example.hotelservice.mapper;

import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.dto.DestinationDTO;

public class DestinationAdapter {
	public static Destination convertDtoTo(DestinationDTO destinationDTO) {
		Destination destination=new Destination();
		destination.setId(destinationDTO.getId());
		destination.setName(destinationDTO.getName());
		destination.setImageUrl(destinationDTO.getImageUrl());
		return destination;
	}

}
