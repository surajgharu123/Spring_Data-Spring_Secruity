package com.security.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
//This is a centralized Class in entire Application. The Entire Security Of My Application written inside
// or Configured inside this Class

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
// this method used for Authentication
		auth.inMemoryAuthentication().withUser("Tom").password("suraj123").roles("USER").and().withUser("Jerry")
				.password("jerry123").roles("ADMIN");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
//Used for Authorization
	}

}