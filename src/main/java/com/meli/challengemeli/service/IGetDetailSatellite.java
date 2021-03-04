package com.meli.challengemeli.service;

import java.util.List;

import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface IGetDetailSatellite {

	List<SateliteSplit> findAll() throws BusinessException;

}
