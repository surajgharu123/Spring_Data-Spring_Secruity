package com.zensar.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStockIdException extends RuntimeException {
	
	private String message;

	public InvalidStockIdException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidStockIdException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InvalidStockIdException [message=" + message + "]";
	}
	
}
