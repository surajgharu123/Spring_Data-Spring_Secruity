package com.olx.login.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.login.dto.User;
import com.olx.login.security.JwtUtil;
import com.olx.login.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/login")
@CrossOrigin(origins = "*")

public class LoginController {
	
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/user/authenticate", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "user Auth", notes = "authenticating a user using tokens")
    public String authenticate(@RequestBody User user) throws AuthenticationException {
	return loginService.authenticate(user);
    }

    @DeleteMapping(value = "/user/logout")
    @ApiOperation(value = "logs out  a user", notes = "logs out a user session")
    public boolean logout(@RequestHeader("auth-token") String authToken) {
	return loginService.logout(authToken);
    }

    // 4
    @GetMapping(value = "/user/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

    public User getUser(@RequestHeader("auth-token") String authToken) {
	return loginService.getUser();

    }

    // 3
    @PostMapping(value = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "create a new user", notes = "This REST api post a new user")
    public User registerUser(@RequestBody User user) {
	return loginService.registerUser(user);
    }

    @GetMapping(value = "/token/validate", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "token Validation", notes = " validates a token ")
    public ResponseEntity<Boolean> validateJWT(@RequestHeader("Authorization") String authToken) {
	return new ResponseEntity<Boolean>(loginService.validateJwtToken(authToken), HttpStatus.OK);
    }

}