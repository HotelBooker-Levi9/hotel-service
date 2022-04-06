package com.example.hotelservice.repository;

import com.example.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByIsDeleted(boolean isDeleted);

    @Query("SELECT r FROM Hotel r WHERE r.isDeleted IS FALSE AND r.id NOT IN :unavailable AND :guestNum <= r.capacity " +
            "AND r.name LIKE %:hotelName% AND r.city.name LIKE %:cityName% AND r.city.destination.name LIKE %:destinationName% AND r.pricePerDay <= :pricePerDay")
    List<Hotel> findSearchResults(@Param("hotelName") String hotelName, @Param("pricePerDay") Integer pricePerDay, @Param("cityName") String cityName,
                                  @Param("destinationName") String destinationName, @Param("unavailable") List<Long> unavailableHotels, @Param("guestNum") Integer guestNum);

    @Query("SELECT r FROM Hotel r WHERE r.isDeleted IS FALSE AND :guestNum <= r.capacity " +
            "AND r.name LIKE %:hotelName% AND r.city.name LIKE %:cityName% AND r.city.destination.name LIKE %:destinationName% AND r.pricePerDay <= :pricePerDay")
    List<Hotel> findSearchResultsWhenDontHaveUnavailable(@Param("hotelName") String hotelName, @Param("pricePerDay") Integer pricePerDay, @Param("cityName") String cityName,
                                  @Param("destinationName") String destinationName, @Param("guestNum") Integer guestNum);
}
