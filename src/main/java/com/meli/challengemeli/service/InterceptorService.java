package com.meli.challengemeli.service;

import java.util.List;

import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.Satelite;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface InterceptorService {
	
	Coordenadas getLocation(List<Double> allDistance,  List<String> satelites) throws BusinessException;
	
	String getMessage(List<List<String>> messages) throws BusinessException;

	ResponseMessage validateInfo(List<Satelite> satelites) throws BusinessException;
	
}
