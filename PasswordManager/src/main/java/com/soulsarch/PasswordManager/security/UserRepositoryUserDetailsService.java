package com.soulsarch.PasswordManager.security;

import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRepositoryUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found"
        );
    }
}
