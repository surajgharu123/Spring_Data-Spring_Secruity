package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.*;

public interface UserRepo extends JpaRepository<UserDetailsEntity, Integer>  {
	
	public List<UserDetailsEntity> findByUsername(String username);
}
