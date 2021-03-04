package com.meli.challengemeli.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.Satelite;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.ILocationService;
import com.meli.challengemeli.service.IMessageService;
import com.meli.challengemeli.service.InterceptorService;
import com.meli.challengemeli.transversal.exception.BusinessException;

@Service
public class InterceptorServiceImpl implements InterceptorService {	
	
	@Autowired
	private ILocationService locationService;
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private SatelliteRepository satelliteRepository;
	
	@Override
	public ResponseMessage validateInfo(List<Satelite> satelites) throws BusinessException {
		
		ResponseMessage response = new ResponseMessage();				
		
		if(!validateSatellite(satelites)) {
			throw new BusinessException("No todos los satelites ingresados estan en operación");
		}		
		
		List<List<String>> allMessages = new ArrayList<>();
		List<Double> allDistance = new ArrayList<>();
		List<String> satelitesName = new ArrayList<>();
		satelites.stream().forEach(item -> {
			allMessages.add(item.getMessage());
			allDistance.add(item.getDistance());
			satelitesName.add(item.getName());
		});		
		response.setMessage(messageService.getMessage(allMessages));	
		response.setPosition(locationService.getLocation(allDistance,satelitesName));
		
		return response;
	}
	
	private boolean validateSatellite(List<Satelite> satelites) {		
		return satelites.stream().allMatch(satelite -> satelliteRepository.findById(satelite.getName().toLowerCase()).isPresent()) ;		
	}

}
