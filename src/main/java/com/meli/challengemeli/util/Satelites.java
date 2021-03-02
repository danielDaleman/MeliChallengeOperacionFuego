package com.meli.challengemeli.util;

import java.util.HashMap;
import java.util.Map;

import com.meli.challengemeli.models.Coordenadas;

public class Satelites {		
	
	private Satelites() {
		super();
	}
	
	public static final Map<String,Coordenadas> SATELITES_EN_SERVICIO = new HashMap<>();
	static{
		SATELITES_EN_SERVICIO.put("kenobi", new Coordenadas(-500,-200));
		SATELITES_EN_SERVICIO.put("skywalker", new Coordenadas(100,-100));
		SATELITES_EN_SERVICIO.put("sato", new Coordenadas(500,100));
	}
	
}
