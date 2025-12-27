package com.opstree.password_manager_backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opstree.password_manager_backend.entity.PasswordEntry;
import com.opstree.password_manager_backend.entity.User;
import com.opstree.password_manager_backend.repository.PasswordRepository;
import com.opstree.password_manager_backend.repository.UserRepository;
import com.opstree.password_manager_backend.util.EncryptionUtil;
import com.opstree.password_manager_backend.util.PasswordGeneratorUtil;
import com.opstree.password_manager_backend.util.PasswordStrengthUtil;

/**
 * Service layer for Password Entry management.
 * Supports add, list, update and delete password functionality
 * along with masking, expiry and validation.
 */


@Service
public class PasswordService {

	@Autowired
	private PasswordRepository passwordRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EncryptionUtil encryptionUtil;

	@Autowired
	private PasswordStrengthUtil strengthUtil;

	@Autowired
	private PasswordGeneratorUtil generator;

	// ADD PASSWORD
	
	/**
	 * Adds a new password entry for a user.
	 * Validates password strength and applies AES encryption
	 *
	 * @param username user who owns password
	 * @param appName application/site name
	 * @param plainPassword raw password
	 * @param expiry expiry date (optional)
	 * @return saved PasswordEntry
	 */

	
	public PasswordEntry addPassword(String username, String appName, String plainPassword, String expiry) {

		if (!strengthUtil.isStrong(plainPassword)) {
			throw new RuntimeException("Weak password");
		}

		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		PasswordEntry entry = new PasswordEntry();
		entry.setAppName(appName);
		entry.setEncryptedPassword(encryptionUtil.encrypt(plainPassword));
		entry.setUser(user);

		if (expiry != null && !expiry.isEmpty()) {
			entry.setExpiryDate(LocalDate.parse(expiry));
			entry.setExpired(LocalDate.parse(expiry).isBefore(LocalDate.now()));
		}

		return passwordRepository.save(entry);
	}
	
//	GENERATE PASSWORD...
	public String generatePassword() {
	    return generator.generate();
	}

//	LIST PASSWORD
	
	/**
	 * Returns list of passwords for given user.
	 * Applies expiry check dynamically and masks password in response.
	 */

	public List<PasswordEntry> listPasswords(String username) {

	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    List<PasswordEntry> list = passwordRepository.findByUser(user);

	    for (PasswordEntry entry : list) {

	        // expiry check
	        if (entry.getExpiryDate() != null) {
	            entry.setExpired(entry.getExpiryDate().isBefore(LocalDate.now()));
	        }

	        // mask password (secure response)
	        String encrypted = entry.getEncryptedPassword();
	        if (encrypted != null && !encrypted.isEmpty()) {
	            int len = encrypted.length();
	            int show = Math.min(4, len);   // last 4 chars visible (like real apps)
	            String masked =
	                    "*".repeat(len - show) +
	                    encrypted.substring(len - show);

	            entry.setEncryptedPassword(masked);
	        }
	    }
	    return list;
	}

//	UPDATE PASSWORD
	
	/**
	 * Updates password entry and re-encrypts new password.
	 * Also updates expiry and expired flag accordingly.
	 */

	
	public PasswordEntry updatePassword(Long id, String newPassword, String expiryDate) {

	    PasswordEntry entry = passwordRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Password entry not found"));

	    if(!strengthUtil.isStrong(newPassword)) {
	        throw new RuntimeException("Weak password");
	    }

	    entry.setEncryptedPassword(encryptionUtil.encrypt(newPassword));

	    if(expiryDate != null && !expiryDate.isEmpty()) {
	        entry.setExpiryDate(LocalDate.parse(expiryDate));
	        entry.setExpired(entry.getExpiryDate().isBefore(LocalDate.now()));
	    }

	    return passwordRepository.save(entry);
	}

//	DELETE...
	
	/**
	 * Deletes password entry safely.
	 * Ensures entry exists before deleting.
	 */

	
	public void deletePassword(Long id) {
	    if(!passwordRepository.existsById(id)) {
	        throw new RuntimeException("Password entry not found");
	    }
	    passwordRepository.deleteById(id);
	}

	
}
