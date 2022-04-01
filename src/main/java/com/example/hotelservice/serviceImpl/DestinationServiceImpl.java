package com.example.hotelservice.serviceImpl;

import com.example.hotelservice.repository.DestinationRepository;
import com.example.hotelservice.service.CityService;
import com.example.hotelservice.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;
}
