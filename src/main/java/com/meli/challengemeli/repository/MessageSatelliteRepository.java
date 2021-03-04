package com.meli.challengemeli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.meli.challengemeli.entitys.MessageSatellite;
import com.meli.challengemeli.entitys.Satellite;

public interface MessageSatelliteRepository extends JpaRepository<MessageSatellite, Long>{
	
	List<MessageSatellite> findByIdSatellite(Satellite idSatellite);
			
	@Query(" SELECT p.message FROM MessageSatellite p"
		  +" WHERE p.idSatellite.nombre = :idSatellite")
	List<String> findAllDetailBySatellite(String idSatellite);	
	
	@Modifying
	@Query("DELETE FROM MessageSatellite p WHERE p.idSatellite.nombre = :idSatellite")
	void deleteByNameSatellite(String idSatellite);
	
}
