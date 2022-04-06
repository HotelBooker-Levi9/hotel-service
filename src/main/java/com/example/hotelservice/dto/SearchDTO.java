package com.example.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private String hotelName;
    private Integer pricePerDay;
    private String cityName;
    private String destinationName;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer guestNum;
}
