package com.opstree.password_manager_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opstree.password_manager_backend.entity.PasswordEntry;
import com.opstree.password_manager_backend.entity.User;

public interface PasswordRepository extends JpaRepository<PasswordEntry, Long> {

	List<PasswordEntry> findByUser(User user);
}
