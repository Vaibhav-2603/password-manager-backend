package com.opstree.password_manager_backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity representing a password entry stored by a user.
 * Supports AES encrypted password storage, expiry feature
 * and multi-tenant isolation through User mapping.
 */


@Entity
	@Table(name = "password_entries")
	public class PasswordEntry {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String appName;

	    @Column(nullable = false)
	    private String encryptedPassword;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @Column
	    private LocalDate expiryDate;
	   
	    private Boolean expired = false;

	    
	    public LocalDate getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
		}

		public Boolean getExpired() {
		    return expired == null ? false : expired;
		}

		public void setExpired(Boolean expired) {
		    this.expired = expired;
		}


		// getters & setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getAppName() {
	        return appName;
	    }

	    public void setAppName(String appName) {
	        this.appName = appName;
	    }

	    public String getEncryptedPassword() {
	        return encryptedPassword;
	    }

	    public void setEncryptedPassword(String encryptedPassword) {
	        this.encryptedPassword = encryptedPassword;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }
	}
	
