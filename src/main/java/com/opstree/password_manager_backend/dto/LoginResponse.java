package com.opstree.password_manager_backend.dto;

/**
 * DTO used as response for successful login.
 * Contains only safe user information like id and username
 * without exposing sensitive fields such as password.
 */

public class LoginResponse {

	private Long id;
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
