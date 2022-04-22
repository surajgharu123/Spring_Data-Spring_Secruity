package com.olx.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.entity.UserDetailsEntity;
import com.olx.repository.UserRepo;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		List<UserDetailsEntity> userDetialsEntitiesList = userRepo.findByUsername(username);
		
		if(userDetialsEntitiesList == null || userDetialsEntitiesList.size() == 0)
		{
			throw new UsernameNotFoundException(username);
		}
		
		UserDetailsEntity userDetailsEntity = userDetialsEntitiesList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String roles = userDetailsEntity.getRoles();
		String rolesList[] = roles.split(",");
		for(String role : rolesList)
		{
			authorities.add(new SimpleGrantedAuthority(role));
		}
 		UserDetails userDetails = new User(username, this.passwordEncoder.encode(userDetailsEntity.getPassword()),authorities);
		return userDetails;
	}

}
