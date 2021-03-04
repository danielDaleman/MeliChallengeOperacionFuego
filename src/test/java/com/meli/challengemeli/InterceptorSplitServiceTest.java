package com.meli.challengemeli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.challengemeli.entitys.MessageSatellite;
import com.meli.challengemeli.entitys.Satellite;
import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.ILocationService;
import com.meli.challengemeli.service.IMessageService;
import com.meli.challengemeli.service.implementation.InterceptorSplitServiceImpl;
import com.meli.challengemeli.transversal.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
class InterceptorSplitServiceTest {
	
	@InjectMocks
	InterceptorSplitServiceImpl service;
	
	@Mock
	SatelliteRepository satelliteRepository;
	
	@Mock
	MessageSatelliteRepository messageSatelliteRepository;		
	
	@Mock
	IMessageService messageService;
	
	@Mock
	ILocationService locationService;
	
	private List<Satellite> satelites;
	private List<MessageSatellite> messageSatelites;
	
	@BeforeEach
	void init() {
		satelites = new ArrayList<>();
		messageSatelites = new ArrayList<>();
	}
	
	@Test
	void getMessageOk() {
		messageSatelites.add(new MessageSatellite());				
		when(satelliteRepository.findAll()).thenReturn(satelites);
		when(messageSatelliteRepository.findAll()).thenReturn(messageSatelites);		
		try {
			when(messageService.getMessage(Mockito.anyList())).thenReturn("This is a message");
			when(locationService.getLocation(Mockito.anyList(),Mockito.anyList())).thenReturn(new Coordenadas(100, 200));			
			ResponseMessage response = service.getMessage();
			assertEquals("This is a message", response.getMessage());
		} catch (BusinessException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	void getMessageEmpty() {						
		when(satelliteRepository.findAll()).thenReturn(satelites);
		when(messageSatelliteRepository.findAll()).thenReturn(messageSatelites);		
		try {						
			service.getMessage();
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true);
		}
		
	}

}
