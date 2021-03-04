package com.meli.challengemeli.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.service.ILocationService;
import com.meli.challengemeli.transversal.exception.BusinessException;
import com.meli.challengemeli.util.Satelites;
import com.meli.challengemeli.util.Trilateracion;

@Service
public class LocationService implements ILocationService{
	
	@Override	
	public Coordenadas getLocation(List<Double> allDistance, List<String> satelites) throws BusinessException {
		
		if(allDistance.isEmpty() || allDistance.size() < 3 || allDistance.size() > 3) {
			throw new BusinessException("Se debe contar con la información de los 3 Satelites.");
		}		
		
		List<Coordenadas> coordenadas = new ArrayList<>();		
		satelites.stream().forEach(item -> coordenadas.add(Satelites.SATELITES_EN_SERVICIO.get(item)));				
		
		return Trilateracion.findLocation(coordenadas,allDistance);			
		
	}
	
}
