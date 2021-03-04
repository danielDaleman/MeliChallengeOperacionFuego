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
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.implementation.LocationService;
import com.meli.challengemeli.transversal.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
	
	@InjectMocks
	LocationService service;
	
	@Mock
	SatelliteRepository satelliteRepository;
	
	private List<Double> distancias;
	private List<String> satelites;	
	
	@BeforeEach
	void init() {
		distancias = new ArrayList<>();		
		satelites = new ArrayList<>();
		satelites.add("skywalker");
		satelites.add("sato");
		satelites.add("kenobi");
		
	}
	
	@Test
	void getLocationOK() {
		distancias.add(115.5);
		distancias.add(142.7);
		distancias.add(100.0);
		var sate = new Satellite();
		sate.setX(100);
		sate.setY(-100);
		Optional<Satellite> satellite = Optional.ofNullable(sate);		
		when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);		
		
		try {
			service.getLocation(distancias,satelites);
			assertTrue(true);
		} catch (BusinessException e) {
			assertTrue(false); 
		}				
	}
	
	@Test
	void getLocationEmpty() {								
		try {
			service.getLocation(distancias,satelites);
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true); 
		}				
	}

}
