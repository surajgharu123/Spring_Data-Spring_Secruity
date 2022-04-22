package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestBodyException extends RuntimeException {
	
    String message;

	
	public InvalidRequestBodyException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidRequestBodyException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidRequestBodyException [message=" + message + "]";
	}

}
