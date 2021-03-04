package com.meli.challengemeli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.challengemeli.entitys.Satellite;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, String>{		
	
}	
