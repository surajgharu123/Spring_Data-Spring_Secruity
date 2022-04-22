package com.zensar.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




//@ControllerAdvice is a specialization of the @Component annotation which allows to handle 
//exceptions across the whole application in one global handling component. It can be viewed 
//as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
//
//It declares @ExceptionHandler, @InitBinder, or @ModelAttribute methods to be shared across 
//multiple @Controller classes.
//
//ResponseEntityExceptionHandler is a convenient base class for @ControllerAdvice classes 
//that wish to provide centralized exception handling across all @RequestMapping methods 
//through @ExceptionHandler methods. It provides an methods for handling internal Spring 
//MVC exceptions. It returns a ResponseEntity in contrast to DefaultHandlerExceptionResolver 
//which returns a ModelAndView.
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(value = InvalidStockIdException.class)
	public ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"Invalid stock id \"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}

}
