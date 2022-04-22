package com.blog.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(value = InvalidRequestBodyException.class)
	public ResponseEntity<Object> handleConflictForCredentials(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidRequestBodyException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
		return response;
	}
	
	
	@ExceptionHandler(value = InvalidIdException.class)
	public ResponseEntity<Object> handleConflictForAuthToken(RuntimeException exception, WebRequest request) {
		//String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
		return response;
	} 
	
	
}
