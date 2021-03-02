package com.meli.challengemeli.service;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface InterceptorSplitService {
	
	void addSatellite(SateliteSplit satelite) throws BusinessException;

	ResponseMessage getMessage() throws BusinessException;
	
}
