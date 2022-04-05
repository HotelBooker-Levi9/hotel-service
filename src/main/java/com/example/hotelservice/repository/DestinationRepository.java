package com.example.hotelservice.repository;

import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.Hotel;
import com.example.hotelservice.model.dto.DestinationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
	
	
}
