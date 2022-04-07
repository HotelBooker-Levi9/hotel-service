package com.example.hotelservice.mapper;

import java.util.ArrayList;
import java.util.List;

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
	public static DestinationDTO convertToDTO(Destination destination) {
		DestinationDTO destinationDTO=new DestinationDTO();
		destinationDTO.setId(destination.getId());
		destinationDTO.setName(destination.getName());
		destinationDTO.setImageUrl(destination.getImageUrl());
		return destinationDTO;
	}
	public static List<DestinationDTO> convertListToDTO(List<Destination> destinations) {
		List<DestinationDTO> destinationsDTO=new ArrayList<>();
		for (Destination destination : destinations )
			destinationsDTO.add(convertToDTO(destination));

		return destinationsDTO;
		
	}
}
