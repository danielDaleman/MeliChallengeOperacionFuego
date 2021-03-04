package com.meli.challengemeli.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meli.challengemeli.entitys.MessageSatellite;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.ICreateDetailSatellite;
import com.meli.challengemeli.transversal.exception.BusinessException;

@Service
public class CreateDetailSatellite implements ICreateDetailSatellite {
	
	@Autowired
	private SatelliteRepository satelliteRepository;
	
	@Autowired 
	private MessageSatelliteRepository messageSatelliteRepository;
	
	@Value("${message.error.enOperacion}")
	private String enOperacion;
	
	@Value("${message.error.infoCompleta}")
	private String infoCompleta;
	
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void addDetailSatellite(SateliteSplit satelite, String satelliteName) throws BusinessException {
		
		var satellite = satelliteRepository.findById(satelliteName.toLowerCase());
		
		if(!satellite.isPresent()) {
			throw new BusinessException(enOperacion);
		}		
		
		var validate = messageSatelliteRepository.findByIdSatellite(satellite.get());
		
		if(!validate.isEmpty()) {
			throw new BusinessException(infoCompleta);
		}
		
		satellite.get().setDistance(satelite.getDistance());
		
		satelite.getMessage().stream().forEach(item -> {
			MessageSatellite message = new MessageSatellite();
			message.setIdSatellite(satellite.get());
			message.setMessage(item);
			messageSatelliteRepository.save(message);
		});		
		
		satelliteRepository.save(satellite.get());
		
	}

}
