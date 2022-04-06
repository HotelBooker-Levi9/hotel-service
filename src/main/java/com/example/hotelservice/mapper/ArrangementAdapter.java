package com.example.hotelservice.mapper;

import com.example.hotelservice.dto.Arrangement;
import com.example.hotelservice.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class ArrangementAdapter {

    public static List<Arrangement> convertHotelListToArrangementList(List<Hotel> hotels) {
        List<Arrangement> arrangements = new ArrayList<>();
        for(Hotel hotel : hotels)
            arrangements.add(convertHotelToArrangement(hotel));
        return arrangements;
    }

    private static Arrangement convertHotelToArrangement(Hotel hotel) {
        return new Arrangement(hotel.getId(), hotel.getName(), hotel.getImageUrl(), hotel.getDescription(), hotel.getPricePerDay(),
                                hotel.getCapacity(), hotel.getIsDeleted(), hotel.getCity().getName(), hotel.getCity().getDestination().getName());
    }
}
