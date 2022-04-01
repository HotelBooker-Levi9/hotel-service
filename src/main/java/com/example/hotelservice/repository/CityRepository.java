package com.example.hotelservice.repository;

import com.example.hotelservice.model.City;
import com.example.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
