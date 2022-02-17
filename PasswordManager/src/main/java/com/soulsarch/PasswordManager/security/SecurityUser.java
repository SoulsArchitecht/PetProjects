package com.soulsarch.PasswordManager.security;

import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean activeTrue;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActiveTrue();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActiveTrue();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActiveTrue();
    }

    @Override
    public boolean isEnabled() {
        return isActiveTrue();
    }

    public static UserDetails fromUser (User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities());

    }
}
