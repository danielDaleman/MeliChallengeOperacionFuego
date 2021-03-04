package com.meli.challengemeli.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.SateliteSplit;
import com.meli.challengemeli.service.ICreateDetailSatellite;
import com.meli.challengemeli.service.IDeleteDetailSatellite;
import com.meli.challengemeli.service.IGetDetailSatellite;
import com.meli.challengemeli.service.InterceptorSplitService;
import com.meli.challengemeli.transversal.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/topsecret_split")
@Api(tags = "topsecret_split", value = "/topsecret_split")
@Slf4j
public class InterceptorSplitController {
	
	@Autowired
	private InterceptorSplitService interceptorSplitService;
	
	@Autowired 
	private ICreateDetailSatellite createDetailSatellite;
	
	@Autowired 
	private IGetDetailSatellite getDetailSatellite;
	
	@Autowired
	private IDeleteDetailSatellite deleteDetailSatellite;
	
	@ApiOperation(value = "Carga el detalle del mensaje interceptado por el satelite")
	@PostMapping(path = "/{satelliteName}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)		
	@ResponseStatus(HttpStatus.OK)
	public void uploadDetailSatelite(@PathVariable String satelliteName, @RequestBody @Valid  SateliteSplit satelite) {		
		try {
			createDetailSatellite.addDetailSatellite(satelite, satelliteName);
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Carga la información especifica de un satelite")
	@GetMapping(path = "/generateMessage", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ResponseMessage> getMessage() {		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(interceptorSplitService.getMessage());
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Muestra todos los registros de detalle de los satelites almacenados")
	@GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<SateliteSplit>> findAll() {		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(getDetailSatellite.findAll());
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Elimina todos los detalles de los satelites (distancia y mensajes)")
	@DeleteMapping(path="/deleteAllDetaill", produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllDetail() {		
		try {
			deleteDetailSatellite.deleteAll();
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Elimina el detalle de un satelite por nombre")
	@DeleteMapping(path="/deleteByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.OK)
	public void deleteByName(@PathVariable String name) {		
		try {
			deleteDetailSatellite.deletByName(name);
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	
}
