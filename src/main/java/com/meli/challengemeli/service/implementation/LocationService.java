package com.meli.challengemeli.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.repository.SatelliteRepository;
import com.meli.challengemeli.service.ILocationService;
import com.meli.challengemeli.transversal.exception.BusinessException;
import com.meli.challengemeli.util.Trilateracion;

@Service
public class LocationService implements ILocationService{
	
	@Autowired
	private SatelliteRepository satelliteRepository;
	
	@Value("${message.error.infoIncompleta}")
	private String infoIncompleta;
	
	@Override	
	public Coordenadas getLocation(List<Double> allDistance, List<String> satelites) throws BusinessException {
		
		if(allDistance.isEmpty() || allDistance.size() < 3 || allDistance.size() > 3) {
			throw new BusinessException(infoIncompleta);
		}		
		
		List<Coordenadas> coordenadas = new ArrayList<>();					
		satelites.stream().forEach(item -> {
			var sateliteTemp = satelliteRepository.findById(item);
			if(sateliteTemp.isPresent())
				coordenadas.add(new Coordenadas(sateliteTemp.get().getX(),sateliteTemp.get().getY()));
		});				
		
		return Trilateracion.findLocation(coordenadas,allDistance);			
		
	}
	
}
