package com.example.hotelservice.repository;

import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Destination;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
	@Query("SELECT c FROM City c WHERE c.destination.id = :destinationId " + "AND c.isDeleted = 0 ")
	List<City> findAllCitiesForDestination(@Param("destinationId") Long destinationId);

}
