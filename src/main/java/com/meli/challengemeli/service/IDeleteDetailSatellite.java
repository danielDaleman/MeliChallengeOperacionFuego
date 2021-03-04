package com.meli.challengemeli.service;

import com.meli.challengemeli.transversal.exception.BusinessException;

public interface IDeleteDetailSatellite {

	void deleteAll() throws BusinessException;

	void deletByName(String name) throws BusinessException;

}
 