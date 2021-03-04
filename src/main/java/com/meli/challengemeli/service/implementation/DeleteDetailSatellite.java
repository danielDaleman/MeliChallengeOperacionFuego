package com.meli.challengemeli.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.IDeleteDetailSatellite;
import com.meli.challengemeli.transversal.exception.BusinessException;

@Service
public class DeleteDetailSatellite implements IDeleteDetailSatellite{
	
	@Autowired
	private SatelliteRepository satelliteRepository;	
	
	@Autowired 
	private MessageSatelliteRepository messageSatelliteRepository;
	
	@Value("${message.error.enOperacion}")
	private String enOperacion;
	
	@Value("${message.info.sinOperacion}")
	private String sinOperacion;
	
	@Transactional
	@Override
	public void deleteAll() throws BusinessException {
		
		var satellites = satelliteRepository.findAll();
		if(satellites.isEmpty()) {
			throw new BusinessException(sinOperacion);
		}		
		
		satellites.stream().forEach(item -> {
			item.setDistance(0);
			satelliteRepository.save(item);
		});
		
		messageSatelliteRepository.deleteAll();
	}
	
	@Transactional
	@Override
	public void deletByName(String name) throws BusinessException {
		
		var satelite = satelliteRepository.findById(name);
		
		if(!satelite.isPresent()) {
			throw new BusinessException(enOperacion);
		}
		
		satelite.get().setDistance(0);		
		satelliteRepository.save(satelite.get());			
		
		messageSatelliteRepository.deleteByNameSatellite(name);		
	}
	
}
