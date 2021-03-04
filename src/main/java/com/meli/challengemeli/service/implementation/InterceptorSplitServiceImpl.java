package com.meli.challengemeli.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.ILocationService;
import com.meli.challengemeli.service.IMessageService;
import com.meli.challengemeli.service.InterceptorSplitService;
import com.meli.challengemeli.transversal.exception.BusinessException;

@Service
public class InterceptorSplitServiceImpl implements InterceptorSplitService{
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private ILocationService locationService;	
	
	@Autowired
	private SatelliteRepository satelliteRepository;
	
	@Autowired 
	private MessageSatelliteRepository messageSatelliteRepository;		

	@Override
	public ResponseMessage getMessage() throws BusinessException {				
		
		var satellites = satelliteRepository.findAll();
		
		var details = messageSatelliteRepository.findAll();				
		
		if(details.isEmpty()) {
			throw new BusinessException("No se cuenta con la información necesaria, valide que se cuente con la información de los 3 satelites");
		}			
		
		ResponseMessage response = new ResponseMessage();
		
		List<List<String>> allMessages = new ArrayList<>();
		List<Double> allDistance = new ArrayList<>();
		List<String> satelitesName = new ArrayList<>();
		
		satellites.stream().forEach(item -> {
			allMessages.add(messageSatelliteRepository.findAllDetailBySatellite(item.getNombre()));
			satelitesName.add(item.getNombre());
			allDistance.add(item.getDistance());
		});							
		
		response.setMessage(messageService.getMessage(allMessages));				
		response.setPosition(locationService.getLocation(allDistance,satelitesName));		
		
		return response;
	}	

}
