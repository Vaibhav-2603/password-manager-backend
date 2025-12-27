package com.opstree.password_manager_backend.dto;

/**
 * DTO used to receive password related requests.
 * Carries username for multi-tenant support, application name,
 * plain password (which will be encrypted) and optional expiry date.
 */

public class PasswordRequest {

    private String username;
    private String appName;
    private String password;
    private String expiryDate;

    
    public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
