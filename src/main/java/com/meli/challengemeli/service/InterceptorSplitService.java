package com.meli.challengemeli.service;

import java.util.List;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface InterceptorSplitService {
	
	void addSatellite(SateliteSplit satelite, String satelliteName) throws BusinessException;

	ResponseMessage getMessage() throws BusinessException;

	List<SateliteSplit> findAll() throws BusinessException;

	void deleteAll() throws BusinessException;

	void deletById(String satelliteName) throws BusinessException;
	
}
