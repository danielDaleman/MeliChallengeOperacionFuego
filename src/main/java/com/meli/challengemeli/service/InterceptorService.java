package com.meli.challengemeli.service;

import java.util.List;

import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface InterceptorService {
	
	Coordenadas getLocation(float x, float y) throws BusinessException;
	
	String getMessage(List<List<String>> messages) throws BusinessException;
	
}