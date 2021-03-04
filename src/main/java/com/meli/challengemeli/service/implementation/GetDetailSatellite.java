package com.meli.challengemeli.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.repository.MessageSatelliteRepository;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.IGetDetailSatellite;
import com.meli.challengemeli.transversal.exception.BusinessException;

@Service
public class GetDetailSatellite implements IGetDetailSatellite{
	
	@Autowired
	private SatelliteRepository satelliteRepository;
	
	@Autowired 
	private MessageSatelliteRepository messageSatelliteRepository;
	
	@Value("${message.info.sinOperacion}")
	private String sinOperacion;
	
	@Override
	public List<SateliteSplit> findAll() throws BusinessException {
		
		List<SateliteSplit> response = new ArrayList<>();
		
		var satellites = satelliteRepository.findAll();
		
		if(satellites.isEmpty()) {
			throw new BusinessException(sinOperacion);
		}
		
		satellites.stream().forEach(item ->{
			SateliteSplit detail = new SateliteSplit();
			detail.setName(item.getNombre());
			detail.setDistance(item.getDistance());
			detail.setMessage(messageSatelliteRepository.findAllDetailBySatellite(item.getNombre()));
			response.add(detail);
		});		
		
		return response;
	}

}
