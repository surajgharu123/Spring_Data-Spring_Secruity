package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidIdException extends RuntimeException {
	
	String message;

	
	public InvalidIdException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidIdException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidIdException [message=" + message + "]";
	}
	
	

}
