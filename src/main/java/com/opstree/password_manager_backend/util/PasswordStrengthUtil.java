package com.opstree.password_manager_backend.util;

import org.springframework.stereotype.Component;


/**
 * Utility class to validate password strength.
 *
 * Password is considered strong only if it:
 * 1. Contains at least one uppercase letter (A-Z)
 * 2. Contains at least one lowercase letter (a-z)
 * 3. Contains at least one digit (0-9)
 * 4. Contains at least one special character (@#$%^&+=)
 * 5. Has minimum length of 8 characters
 *
 * This ensures that weak passwords are never accepted
 * and improves application security.
 */


@Component
public class PasswordStrengthUtil {

    public boolean isStrong(String password) {
        return password.matches(
          "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$"
        );
    }
}
