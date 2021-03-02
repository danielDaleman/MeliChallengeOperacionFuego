package com.meli.challengemeli.transversal.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
	extends ResponseEntityExceptionHandler {

		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(
				org.springframework.web.bind.MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {

			List<ValidationError> validationError = new ArrayList<>();

			for (FieldError b : ex.getBindingResult().getFieldErrors()) {
				validationError.add(
						new ValidationError(b.getField(), b.getDefaultMessage()));
			}
			return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
		}
}
