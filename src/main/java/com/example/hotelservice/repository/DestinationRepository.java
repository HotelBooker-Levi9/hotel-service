package com.example.hotelservice.repository;

import com.example.hotelservice.model.Destination;
import com.example.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
