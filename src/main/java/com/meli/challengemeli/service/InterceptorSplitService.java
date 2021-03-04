package com.meli.challengemeli.service;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.transversal.exception.BusinessException;

public interface InterceptorSplitService {	
	ResponseMessage getMessage() throws BusinessException;		
}
