package com.jobportal.controller;

import java.security.Principal;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
	
	@GetMapping(value = "/")
	public String checkAuth(Principal principal) {
		OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) principal;
		return "Hello " + oAuth2AuthenticationToken.getName();
	}
	
	@GetMapping(value = "/hi")
	public String sayHi() {
		//OAuth2AccessToken oAuth2AccessToken = (OAuth2AccessToken) principal;
		return "Hi Suraj" ;
	}

}
