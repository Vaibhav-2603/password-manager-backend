package com.opstree.password_manager_backend.util;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

/**
 * Utility class responsible for generating secure random passwords.
 *
 * This password generator:
 * - Uses SecureRandom for better randomness
 * - Generates 12 character strong passwords
 * - Includes uppercase, lowercase, digits and special characters
 *
 * This feature helps users who do not want
 * to manually create strong passwords.
 */


@Component
public class PasswordGeneratorUtil {

    public String generate() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 12; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
