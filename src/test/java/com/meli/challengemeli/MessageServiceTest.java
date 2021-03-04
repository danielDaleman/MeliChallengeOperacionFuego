package com.meli.challengemeli;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.challengemeli.service.implementation.MessageService;
import com.meli.challengemeli.transversal.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
	
	@InjectMocks
	MessageService service;
	
	private	List<List<String>> messagesEquals;
	private List<List<String>> diferent;
	private List<List<String>> error;
	
	@BeforeEach
	void init() {
		messagesEquals = new ArrayList<>();
		messagesEquals.add(List.of("este", "", "", "mensaje", ""));
		messagesEquals.add(List.of("", "es", "", "", "secreto"));
		messagesEquals.add(List.of("este", "", "un", "", ""));	
		
		diferent = new ArrayList<>();
		diferent.add(List.of("","este", "es", "un", "mensaje"));
		diferent.add(List.of("este", "", "un", "mensaje"));
		diferent.add(List.of("", "", "es", "", "mensaje"));		
		
		error = new ArrayList<>();
		error.add(List.of("","", "es", "", "mensaje"));
		error.add(List.of("", "", "", "mensaje"));
		error.add(List.of("", "", "es", "", "mensaje"));		
	}
	
	@Test
	void getMessageOkListEqualsSize() {
		try {
			String message = service.getMessage(messagesEquals);
			assertEquals("este es un mensaje secreto ", message);						
		} catch (BusinessException e) {
			assertTrue(false);
		}
	}
	
	@Test
	void getMessageOkListNotEqualsSize() {
		try {
			String message = service.getMessage(diferent);
			assertEquals("este es un mensaje ", message);					
		} catch (BusinessException e) {
			assertTrue(false);
		}
	}
	
	@Test
	void getMessageInfoIncompleta() {
		try {
			List<List<String>> messagesEmpy = new ArrayList<>();
			service.getMessage(messagesEmpy);						
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void getMessageIncorrect() {
		try {			
			service.getMessage(error);						
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(true);
		}
	}

}
