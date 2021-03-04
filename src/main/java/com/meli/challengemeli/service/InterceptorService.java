package com.meli.challengemeli.service;

import java.util.List;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.Satelite;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface InterceptorService {		

	ResponseMessage validateInfo(List<Satelite> satelites) throws BusinessException;
	
}
