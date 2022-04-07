package com.example.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelservice.model.dto.CityDTO;
import com.example.hotelservice.model.dto.DestinationDTO;
import com.example.hotelservice.service.DestinationService;

@RestController
@RequestMapping(value = "/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;
  
    
    @PostMapping("/addDestination")
    public ResponseEntity<?> addDestination(@RequestBody DestinationDTO destinationDTO) {
          destinationService.addDestination(destinationDTO);
          return new ResponseEntity<>(HttpStatus.OK);
        
    }
    @PutMapping
    public ResponseEntity<?> updateDestination(@RequestBody DestinationDTO destinationDTO) {
          destinationService.updateDestination(destinationDTO);
          return new ResponseEntity<>(HttpStatus.OK);
        
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   	public ResponseEntity<DestinationDTO> getDestinationById(@PathVariable Long id) {
   		
   		return destinationService.findOne(id);
   	}

       @RequestMapping(value = "/", method = RequestMethod.GET)
   	public ResponseEntity<List<DestinationDTO>> getAllDestinations() {
   		
   		return destinationService.findAll();
   	}
       
       @PutMapping(value="/{id}")
       public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
    	   destinationService.removeDestination(id);
           return new ResponseEntity<>(HttpStatus.OK);
             
          
       }

}
