package com.example.hotelservice.dto;

import com.example.hotelservice.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arrangement {

    private Long id;
    private String name;
    private String imageUrl;
    private String description;
    private Integer pricePerDay;
    private Integer capacity;
    private Boolean isDeleted;
    private String cityName;
    private String destinationName;
}
