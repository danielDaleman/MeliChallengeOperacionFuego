package com.meli.challengemeli;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.Satelite;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.ILocationService;
import com.meli.challengemeli.service.IMessageService;
import com.meli.challengemeli.service.implementation.InterceptorServiceImpl;
import com.meli.challengemeli.transversal.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
class InterceptorServiceTest {
	
	@InjectMocks
	InterceptorServiceImpl service;
	
	@Mock
	SatelliteRepository satelliteRepository;		
	
	@Mock
	IMessageService messageService;
	
	@Mock
	ILocationService locationService;
	
	private List<Satelite> satelites;
	
	@BeforeEach
	void init() {
		satelites = new ArrayList<>();
		var satelite1 = new Satelite();
		satelite1.setName("skywalker");
		var satelite2 = new Satelite();
		satelite2.setName("sato");
		var satelite3 = new Satelite();
		satelite3.setName("kenobi");
		satelites.add(satelite1);
		satelites.add(satelite2);
		satelites.add(satelite3);
	}
	
	@Test
	void validateInfoOk() {
		Optional<Satellite> satellite = Optional.ofNullable(new Satellite());						
		try {
			when(messageService.getMessage(Mockito.anyList())).thenReturn("This is a message");
			when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);
			when(locationService.getLocation(Mockito.anyList(),Mockito.anyList())).thenReturn(Mockito.any(Coordenadas.class));			
			ResponseMessage response = service.validateInfo(satelites);
			assertEquals("This is a message", response.getMessage());
		} catch (BusinessException e) {
			assertTrue(false);
		}		
	}
	
	@Test
	void validateInfoEmpty() {
		Optional<Satellite> satellite = Optional.empty();						
		try {			
			when(satelliteRepository.findById(Mockito.anyString())).thenReturn(satellite);			
			service.validateInfo(satelites);
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true);
		}		
	}

}
