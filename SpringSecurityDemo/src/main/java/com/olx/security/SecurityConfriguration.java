package com.olx.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//This is a centralized Class in entire Application. The Entire Security Of My Application written inside
// or Configured inside this Class

public class SecurityConfriguration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
// this method used for Authentication
		
		auth.userDetailsService(userDetailsService);
		// PasswordEncoder : PasswordEncoder is a Spring Security interface which contains a very flexible 
		//mechanism when it comes to password storage.
		
		
		/**
		 * Committing out because here we using hardcarded data
		 
		auth.inMemoryAuthentication()
		.withUser("tom").password(this.passwordEncoder.encode("suraj123")).roles("USER")
		.and()
		.withUser("Jerry").password(this.passwordEncoder.encode("jerry123")).roles("ADMIN");
		*/
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
//Used for Authorization
		
		http.authorizeRequests().antMatchers("/user").hasAnyRole("USER", "ADMIN") 
		.antMatchers("/admin").hasAnyRole("ADMIN").antMatchers("/all").permitAll().and().formLogin();
	}

}
