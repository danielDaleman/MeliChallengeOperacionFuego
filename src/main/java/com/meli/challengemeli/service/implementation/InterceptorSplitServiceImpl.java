package com.meli.challengemeli.service.implementation;

import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.service.InterceptorSplitService;
import com.meli.challengemeli.transversal.exception.BusinessException;

@Service
public class InterceptorSplitServiceImpl implements InterceptorSplitService{

	@Override
	public void addSatellite(SateliteSplit satelite) throws BusinessException {
		
	}

	@Override
	public ResponseMessage getMessage() throws BusinessException {
		return null;
	}

}
