package com.meli.challengemeli.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.service.InterceptorService;
import com.meli.challengemeli.service.InterceptorSplitService;
import com.meli.challengemeli.transversal.exception.BusinessException;
import com.meli.challengemeli.util.ValidateUtil;

@Service
public class InterceptorSplitServiceImpl implements InterceptorSplitService{
	
	@Autowired
	private InterceptorService interceptorService;
	
	private static List<SateliteSplit> satelites = new ArrayList<>();
	
	@Override
	public void addSatellite(SateliteSplit satelite, String satelliteName) throws BusinessException {
		if(!ValidateUtil.existeSatelite(satelliteName)) {
			throw new BusinessException("El satelite ingresado no esta en operación");
		}
		if(satelites.size() == 3) {
			throw new BusinessException("Solo existen 3 saltelites en operación, y ya estan registrados");
		}		
		
		var validate = satelites.stream().filter(item -> item.getName().equals(satelliteName)).collect(Collectors.toList());
		
		if(!validate.isEmpty()) {
			throw new BusinessException("Ya se encuentra registrado en el sistema");
		}
		
		satelite.setName(satelliteName);
		satelites.add(satelite);
	}

	@Override
	public ResponseMessage getMessage() throws BusinessException {				

		if(satelites.isEmpty() || satelites.size() < 3 || satelites.size() > 3) {
			throw new BusinessException("No se cuenta con la información necesaria, deben existir 3 satelites y existen acualmente " + satelites.size());
		}
		
		ResponseMessage response = new ResponseMessage();
		
		List<List<String>> allMessages = new ArrayList<>();
		List<Double> allDistance = new ArrayList<>();
		List<String> satelitesName = new ArrayList<>();
		satelites.stream().forEach(item -> {
			allMessages.add(item.getMessage());
			allDistance.add(item.getDistance());
			satelitesName.add(item.getName());
		});		
		
		response.setMessage(interceptorService.getMessage(allMessages));	
		response.setPosition(interceptorService.getLocation(allDistance,satelitesName));
		
		
		return response;
	}

	@Override
	public List<SateliteSplit> findAll() throws BusinessException {
		return satelites;
	}

	@Override
	public void deleteAll() throws BusinessException {
		satelites.clear();
		
	}

	@Override
	public void deletById(String satelliteName) throws BusinessException {
		var satelite = satelites.stream().filter(item -> satelliteName.equals(item.getName()))
				  						 .findAny()
				  						 .orElse(null);
		if(satelite == null) {
			throw new BusinessException("Satelite no existe");
		}
		
		satelites.remove(satelite);
		
	}

}
