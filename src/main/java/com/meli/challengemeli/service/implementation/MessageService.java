package com.meli.challengemeli.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meli.challengemeli.service.IMessageService;
import com.meli.challengemeli.transversal.exception.BusinessException;
import com.meli.challengemeli.util.CompareUtil;
import com.meli.challengemeli.util.ValidateUtil;

@Service
public class MessageService implements IMessageService {
	
	@Value("${message.error.infoIncompleta}")
	private String infoIncompleta;
	
	@Value("${message.error.noDecifrar}")
	private String noDecifrar;
	
	@Override
	public String getMessage(List<List<String>> messages) throws BusinessException {					
		
		if(messages.isEmpty() || messages.size() < 3 || messages.size() > 3) {
			throw new BusinessException(infoIncompleta);
		}		
		
		var base = messages.get(0);
		int position = 1;	

		if(CompareUtil.compareSizeList(messages)) {			
			while(position < messages.size()) {				 
				base = CompareUtil.compareListNodoEqualSize(base, messages.get(position));
				position++;				
			}				
		}else {
			var desfaje = 1;			
			while(position < messages.size()) {				
				base = CompareUtil.compareListNodoNotEqualSize(base, desfaje, messages.get(position));
				position++;				
			}
		}			
		
		String messageFinal = ValidateUtil.validateMessage(base,messages);
		
		if(messageFinal.equals("")) {
			throw new BusinessException(noDecifrar);
		}																
		
		return messageFinal;
	}
}
