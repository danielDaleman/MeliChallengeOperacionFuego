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

import com.meli.challengemeli.entitys.MessageSatellite;
import com.meli.challengemeli.entitys.Satellite;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.implementation.CreateDetailSatellite;
import com.meli.challengemeli.transversal.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
class CreateDetailStelliteTest {	
	
	@InjectMocks
	CreateDetailSatellite service;
	
	@Mock
	SatelliteRepository satelliteRepository;		
	
	@Mock
	MessageSatelliteRepository messageSatelliteRepository;	
	
	private SateliteSplit satelite;
	
	@BeforeEach
	void init() {
		satelite = new SateliteSplit();
		satelite.setName("skywalker");
		satelite.setDistance(115.5);
		satelite.setMessage(List.of("", "es", "", "", "secreto"));
	}
	
	@Test
	void addDetailSatelliteOk(){				
		Optional<Satellite> satellite = Optional.ofNullable(new Satellite());
		List<MessageSatellite> message = new ArrayList<>();
		
		when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);				
		when(messageSatelliteRepository.findByIdSatellite(Mockito.any(Satellite.class))).thenReturn(message);				
		
		try {
			service.addDetailSatellite(satelite, "skywalker");
			assertTrue(true);
		} catch (BusinessException e) {
			assertTrue(false);
		}					
	}
	
	@Test
	void addDetailSatelliteNotPresent(){				
		Optional<Satellite> satellite = Optional.empty();		
		when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);					
		
		try {
			service.addDetailSatellite(satelite, "skywalker");
			assertTrue(false);
		} catch (BusinessException e) {			
			assertTrue(true);
		}					
	} 
	
	@Test
	void addDetailSatelliteNotEmpty(){				
		Optional<Satellite> satellite = Optional.ofNullable(new Satellite());
		List<MessageSatellite> message = new ArrayList<>();
		message.add(new MessageSatellite());
		
		when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);				
		when(messageSatelliteRepository.findByIdSatellite(Mockito.any(Satellite.class))).thenReturn(message);				
		
		try {
			service.addDetailSatellite(satelite, "skywalker");
			assertTrue(false);
		} catch (BusinessException e) {			
			assertTrue(true);
		}					
	} 
	
	

}
