package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping(value="/all")
	public String getAll() {
	return "Hello All";
	}
	@GetMapping(value="/user")
	public String getUser() {
	return "Hello User";
	}
	@GetMapping(value="/admin")
	public String getAdmin() {
	return "Hello Admin";
	}

}
