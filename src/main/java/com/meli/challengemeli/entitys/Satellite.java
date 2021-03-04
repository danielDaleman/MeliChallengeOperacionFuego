package com.meli.challengemeli.entitys;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Satellite {
	
	@Id
	private String nombre;
	private double x;
	private double y;
	@Basic(optional = true)
	private double distance;
	
}
