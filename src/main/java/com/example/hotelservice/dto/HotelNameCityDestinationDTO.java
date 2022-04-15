package com.example.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelNameCityDestinationDTO {
    private String hotelName;
    private String cityName;
    private String destinationName;
}
