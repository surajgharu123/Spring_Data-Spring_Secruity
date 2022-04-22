package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserDetailsEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	private String roles;
	
	public UserDetailsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetailsEntity(int id, String password, String roles, String username) {
		super();
		this.id = id;
		this.password = password;
		this.roles = roles;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserDetailsEntity [id=" + id + ", password=" + password + ", roles=" + roles + ", username=" + username
				+ "]";
	}
	
	
	
}
