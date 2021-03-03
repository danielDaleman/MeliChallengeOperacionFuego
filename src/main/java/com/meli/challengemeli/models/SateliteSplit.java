package com.meli.challengemeli.models;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SateliteSplit {
	
	private String name;
	@NotNull(message = "La distancia es obligatoria")
	private double distance;
	@NotNull(message = "El mensaje interceptado es obligatorio")
	@NotEmpty(message = "El mensaje no debe estar vacío")
	private List<String> message;	
}
