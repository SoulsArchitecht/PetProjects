package com.soulsarch.PasswordManager.repository;

import com.soulsarch.PasswordManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail (String userEmail);
}
