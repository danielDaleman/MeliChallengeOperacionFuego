package com.meli.challengemeli.service;

import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface ICreateDetailSatellite {

	void addDetailSatellite(SateliteSplit satelite, String satelliteName) throws BusinessException;

}
