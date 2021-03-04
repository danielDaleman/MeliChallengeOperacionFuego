package com.meli.challengemeli;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.challengemeli.entitys.Satellite;
import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.implementation.DeleteDetailSatellite;
import com.meli.challengemeli.transversal.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
class DeleteDetailSatelliteTest {
	
	@InjectMocks
	DeleteDetailSatellite service;
	
	@Mock
	SatelliteRepository satelliteRepository;		
	
	@Mock
	MessageSatelliteRepository messageSatelliteRepository;	
	
	private List<Satellite> satelites;
	
	@BeforeEach
	void init() {
		satelites = new ArrayList<>();
	}
	
	@Test
	void deleteAllOk() {		 
		satelites.add(new Satellite());
		when(satelliteRepository.findAll()).thenReturn(satelites);	
		
		try {
			service.deleteAll();
			assertTrue(true);
		} catch (BusinessException e) {
			assertTrue(false);			
		}				
	}
	
	@Test
	void deleteAllEmpty() {		
		when(satelliteRepository.findAll()).thenReturn(satelites);		
		try {
			service.deleteAll();
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true);			
		}				
	}
	
	@Test
	void deletByNameOk() {	 
		Optional<Satellite> satellite = Optional.ofNullable(new Satellite()); 
		when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);		
		
		try {
			service.deletByName(Mockito.anyString());
			assertTrue(true);
		} catch (BusinessException e) {
			assertTrue(false);			
		}				
	}
	
	@Test
	void deletByNameEmpty() {	
		Optional<Satellite> satellite = Optional.empty();
		when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);		
		try {
			service.deletByName(Mockito.anyString());
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true);			
		}				
	}
	
 
}
