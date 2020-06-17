package com.studies.jpaquerydsl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ResponseException> handleEntityNotFoundException(EntityNotFoundException ex) {
		
		ResponseException genericException = new ResponseException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<ResponseException>(genericException, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseException> handleIllegalArgumentException(IllegalArgumentException ex) {
		
		ResponseException genericException = new ResponseException(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<ResponseException>(genericException, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseException> handleException(Exception ex) {
		
		ResponseException genericException = 
				new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
		return new ResponseEntity<ResponseException>(genericException, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
