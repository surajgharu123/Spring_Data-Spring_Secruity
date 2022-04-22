package com.olx.login.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.olx.login.dto.User;
import com.olx.login.entity.UserEntity;
import com.olx.exception.InvalidCredentialsException;
import com.olx.login.repository.UserRepo;
import com.olx.login.security.JwtUtil;
import com.olx.login.security.UserDetailsServiceImpl;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    String jwtString;

//    @Override
//    public String authenticate(User user) { // throws AuthenticationException {
//	this.authenticationManager
//		.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
//
//	return "usertoken123";
//    }

//    @Override
//    public String authenticate(User user) {
//    try {
//    this.authenticationManager.authenticate(
//    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
//
//    }
// catch(AuthenticationException ex) {
//     throw new InvalidCredentialsException(ex.toString());
// }
//    

    @Override
    public String authenticate(User user) {
	try {
	    this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
	}

	catch (AuthenticationException ex) {
	    throw new InvalidCredentialsException(ex.toString());
	}

	// TODO Auto-generated method stub
	String jwt = jwtUtil.generateToken(user.getUserName());
	this.jwtString = jwt;
	return jwt;
    }

    @Override
    public boolean logout(String authToken) {
	return true;

    }

    @Override
    public User registerUser(User user) {
	UserEntity userEntity = convertDTOIntoEntity(user);
	userEntity = userRepo.save(userEntity);
	return convertEntityIntoDTO(userEntity);
    }

//    @Override
//    public List<User> getUserDetails(String authToken) {
//	List<UserEntity> userEntity = userRepo.findAll();
//	List<UserDetails> userDto = convertEntityListIntoDTOList(userEntity);
//	return userDto;
//    }

    // get a user
    @Override
    public User getUser(int id) {
	UserEntity uEntity = userRepo.getById(id);
	return convertEntityIntoDTO(uEntity);

    }

//    @Override
//    public String validateToken(String authToken) {
//	return authToken;
//    }

    private UserEntity convertDTOIntoEntity(User user) {
	TypeMap<User, UserEntity> tMap = modelMapper.typeMap(User.class, UserEntity.class);
	UserEntity userEntity = modelMapper.map(user, UserEntity.class);
	return userEntity;
    }

    private User convertEntityIntoDTO(UserEntity userEntity) {
	TypeMap<UserEntity, User> tMap = modelMapper.typeMap(UserEntity.class, User.class);
	User user = modelMapper.map(userEntity, User.class);
	return user;
    }

    @Override
    public User getUser() {
	String tokenString = jwtUtil.extractUsername(jwtString);
	return convertEntityIntoDTO(userRepo.findByUserName(tokenString).get(0));

    }

    @Override
    public boolean validateJwtToken(String authToken) {
	authToken = authToken.substring(7);
	String username = jwtUtil.extractUsername(authToken);
	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	return jwtUtil.validateToken(authToken, userDetails);
    }

    @Override
    public String validateToken(String authToken) {
	// TODO Auto-generated method stub
	return null;
    }

}