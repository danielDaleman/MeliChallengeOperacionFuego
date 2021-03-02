package com.meli.challengemeli.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.challengemeli.models.ResponseMessage;
import com.meli.challengemeli.models.SateliteList;
import com.meli.challengemeli.service.InterceptorService;
import com.meli.challengemeli.transversal.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/topsecret")
@Api(tags = "topsecret", value = "/topsecret")
@Slf4j
public class InterceptorController {
	
	@Autowired
	private InterceptorService interceptorService;
	
	@ApiOperation(value = "Obtener la ubicación de la nave y el mensaje que emite")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)		
	public ResponseEntity<ResponseMessage> createPayCalendar(@RequestBody @Valid  SateliteList satelites) {		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(interceptorService.validateMessage(satelites.getSatellites()));
		} catch (BusinessException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
}
