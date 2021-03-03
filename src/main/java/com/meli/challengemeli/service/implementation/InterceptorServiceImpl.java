package com.meli.challengemeli.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meli.challengemeli.models.Coordenadas;
import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.Satelite;
import com.meli.challengemeli.service.InterceptorService;
import com.meli.challengemeli.transversal.exception.BusinessException;
import com.meli.challengemeli.util.CompareUtil;
import com.meli.challengemeli.util.Satelites;
import com.meli.challengemeli.util.Trilateracion;
import com.meli.challengemeli.util.ValidateUtil;

@Service
public class InterceptorServiceImpl implements InterceptorService {

	@Override	
	public Coordenadas getLocation(List<Double> allDistance, List<String> satelites) throws BusinessException {
		
		if(allDistance.isEmpty() || allDistance.size() < 3 || allDistance.size() > 3) {
			throw new BusinessException("Se debe contar con la información de los 3 Satelites.");
		}		
		
		List<Coordenadas> coordenadas = new ArrayList<>();		
		satelites.stream().forEach(item -> coordenadas.add(Satelites.SATELITES_EN_SERVICIO.get(item)));				
		
		return Trilateracion.findLocation(coordenadas,allDistance);			
		
	}

	@Override
	public String getMessage(List<List<String>> messages) throws BusinessException {					
		
		if(messages.isEmpty() || messages.size() < 3 || messages.size() > 3) {
			throw new BusinessException("Se debe contar con la información de los 3 Satelites.");
		}		
		
		var base = messages.get(0);
		int position = 1;	

		if(CompareUtil.compareSizeList(messages)) {			
			while(position < messages.size()) {				 
				base = CompareUtil.compareListNodoEqualSize(base, messages.get(position));
				position++;				
			}				
		}else {
			var desfaje = 1;//ValidateUtil.validateDesfaje(messages);			
			while(position < messages.size()) {				
				base = CompareUtil.compareListNodoNotEqualSize(base, desfaje, messages.get(position));
				position++;				
			}
		}			
		
		String messageFinal = ValidateUtil.validateMessage(base,messages);
		
		if(messageFinal.equals("")) {
			throw new BusinessException("No se pudo decifrar el mensaje, falta informacion");
		}																
		
		return messageFinal;
	}

	@Override
	public ResponseMessage validateInfo(List<Satelite> satelites) throws BusinessException {
		
		ResponseMessage response = new ResponseMessage();
		
		if(!ValidateUtil.existeSatelite(satelites)) {
			throw new BusinessException("No todos los satelites ingresados estan en operación");
		}
		
		List<List<String>> allMessages = new ArrayList<>();
		List<Double> allDistance = new ArrayList<>();
		List<String> satelitesName = new ArrayList<>();
		satelites.stream().forEach(item -> {
			allMessages.add(item.getMessage());
			allDistance.add(item.getDistance());
			satelitesName.add(item.getName());
		});		
		response.setMessage(getMessage(allMessages));	
		response.setPosition(getLocation(allDistance,satelitesName));
		
		return response;
	}

}
