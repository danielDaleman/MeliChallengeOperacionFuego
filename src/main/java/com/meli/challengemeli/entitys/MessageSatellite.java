package com.meli.challengemeli.entitys;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class MessageSatellite {		
	@Id
	@GeneratedValue		
	private Long id;
	private String message;
	@JoinColumn(name = "idSatellite", referencedColumnName = "nombre")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Satellite idSatellite;
	
}	
