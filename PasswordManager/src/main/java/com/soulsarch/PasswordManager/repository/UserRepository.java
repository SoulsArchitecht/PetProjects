package com.soulsarch.PasswordManager.repository;

import com.soulsarch.PasswordManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findByEmail (String userEmail);

    //Optional<User> findByUsername(String username);

    User findByUsername(String username);
}
