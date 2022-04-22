package com.olx.login.service;

import com.olx.login.dto.User;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    public String authenticate(User user) throws AuthenticationException;

    public boolean logout(String authToken);

    public User registerUser(User user);

    public User getUser(int id);

    public String validateToken(String authToken);

    public User getUser();

    public boolean validateJwtToken(String authToken);

}