package com.meli.challengemeli.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.SateliteSplit;
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
	
	@ApiOperation(value = "Carga la información especifica de un satelite")
	@PostMapping(path = "/{satellite_name}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)		
	@ResponseStatus(HttpStatus.OK)
	public void createPayCalendar(@RequestBody @Valid  SateliteSplit satelite) {		
		try {
			interceptorSplitService.addSatellite(satelite);
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Carga la información especifica de un satelite")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)		
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ResponseMessage> getMessage(@RequestBody @Valid  SateliteSplit satelite) {		
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
	
	
}
