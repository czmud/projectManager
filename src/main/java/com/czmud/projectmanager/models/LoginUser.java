package com.czmud.projectmanager.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginUser {
	
	@NotBlank(message="email is required")
	@Email(message="Must enter a valid email")
	private String email;
	
	@NotBlank(message="Password is required")
	private String password;
	
	public LoginUser() {}

	// GETTERS & SETTERS
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
}
