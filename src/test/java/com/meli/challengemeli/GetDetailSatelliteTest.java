package com.meli.challengemeli;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.challengemeli.entitys.Satellite;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.implementation.GetDetailSatellite;
import com.meli.challengemeli.transversal.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
class GetDetailSatelliteTest {
	
	@InjectMocks
	GetDetailSatellite service;
	
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
	void findAllOk() {
		satelites.add(new Satellite());
		when(satelliteRepository.findAll()).thenReturn(satelites);	
		
		try {
			List<SateliteSplit> list = service.findAll();
			assertFalse(list.isEmpty());
			assertEquals(1, list.size());					
		} catch (BusinessException e) {
			assertTrue(false);			
		}
	}
	
	@Test
	void findAllEmpty() {
		when(satelliteRepository.findAll()).thenReturn(satelites);	
		
		try {
			service.findAll();
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true);			
		}
	} 

}
