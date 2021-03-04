package com.meli.challengemeli.service;

import java.util.List;

import com.meli.challengemeli.transversal.exception.BusinessException;

public interface IMessageService {
	
	String getMessage(List<List<String>> messages) throws BusinessException;
	
}
