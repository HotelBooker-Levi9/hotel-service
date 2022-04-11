package com.example.hotelservice.repository;

import com.example.hotelservice.model.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	@Query("SELECT h FROM Hotel h WHERE h.city.id = :cityId " + "AND h.isDeleted = 0 ")
	List<Hotel> findAllHotelsForCity(@Param("cityId") Long cityId);

}
