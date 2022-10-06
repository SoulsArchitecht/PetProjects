package com.soulsarch.PasswordManager.security;

import com.soulsarch.PasswordManager.entity.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationFrom {

    private String username;

    private String password;

    private String fullname;

    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, phone
        );
    }

}


