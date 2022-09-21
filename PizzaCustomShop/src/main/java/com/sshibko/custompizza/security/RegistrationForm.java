package com.sshibko.custompizza.security;

import com.sshibko.custompizza.model.entity.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String email;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String region;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, email, passwordEncoder.encode(password),
                fullname, street, city, region, zip, phone
        );
    }
}
