package com.olx.login.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.login.entity.UserEntity;
import com.olx.login.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	List<UserEntity> userList = userRepo.findByUserName(username);

	if (userList == null || userList.size() == 0) {
	    throw new UsernameNotFoundException(username);
	}

	UserEntity userEntity = userList.get(0);
	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	UserDetails userDetails = new User(username, passwordEncoder.encode(userEntity.getPassword()), authorities);

	return userDetails;
    }

}