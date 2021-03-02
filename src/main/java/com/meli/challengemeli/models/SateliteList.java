package com.meli.challengemeli.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SateliteList {
	
	@NotNull(message = "La información de los satelites es obligatoria")
	@NotEmpty(message = "La información de los satelites no debe estar vacía")	
	private List<@Valid Satelite> satellites;
}
