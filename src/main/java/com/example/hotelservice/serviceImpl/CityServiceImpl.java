package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.repository.CityRepository;
import com.example.hotelservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
}
