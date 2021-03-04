package com.meli.challengemeli.service;

import java.util.List;

import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface ILocationService {
	
	Coordenadas getLocation(List<Double> allDistance,  List<String> satelites) throws BusinessException;
	
}
