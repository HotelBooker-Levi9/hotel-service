package com.example.hotelservice.service;

import org.springframework.http.ResponseEntity;

public interface CRUDService<T> {
	ResponseEntity<?> add(T newObject);

	ResponseEntity<?> update(T newObject);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findAll();

	ResponseEntity<?> remove(Long id, boolean deleted);

	ResponseEntity<?> remove(Long id);

}
