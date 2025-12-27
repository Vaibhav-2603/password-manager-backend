package com.opstree.password_manager_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.opstree.password_manager_backend.dto.PasswordRequest;
import com.opstree.password_manager_backend.entity.PasswordEntry;
import com.opstree.password_manager_backend.service.PasswordService;

@RestController
@RequestMapping("/passwords")
public class PasswordController {

	/**
	 * Password Controller
	 * Handles password operations like add, list, update, delete & generate.
	 */
	
	@Autowired
	private PasswordService passwordService;

	// ADD PASSWORD
	@PostMapping("/add")
	public PasswordEntry addPassword(@RequestBody PasswordRequest request) {

		return passwordService.addPassword(request.getUsername(), request.getAppName(), request.getPassword(),
				request.getExpiryDate());
	}

	// LIST PASSWORDS
	@PostMapping("/list")
	public List<PasswordEntry> listPasswords(@RequestBody PasswordRequest request) {

		return passwordService.listPasswords(request.getUsername());
	}

//	GENERATE PASSWORD
	@GetMapping("/generate")
	public String generatePassword() {
		return passwordService.generatePassword();
	}

//	UPDATE PASSWORD BY ID
	@PutMapping("/update/{id}")
	public PasswordEntry updatePassword(
	        @PathVariable Long id,
	        @RequestBody PasswordRequest request) {

	    return passwordService.updatePassword(
	            id,
	            request.getPassword(),
	            request.getExpiryDate()
	    );
	}
	
//	DELETE PASSWORD BY ID
	@DeleteMapping("/delete/{id}")
	public String deletePassword(@PathVariable Long id) {
	    passwordService.deletePassword(id);
	    return "Password entry deleted successfully";
	}

	
}
