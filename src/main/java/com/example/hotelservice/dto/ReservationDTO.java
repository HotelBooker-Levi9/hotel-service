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
public class ReservationDTO {
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer price;
    private Integer guestNumber;
    private Boolean isCanceled;
    private Long hotelId;
}
