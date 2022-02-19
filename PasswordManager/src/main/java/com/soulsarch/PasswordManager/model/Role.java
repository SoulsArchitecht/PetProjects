package com.soulsarch.PasswordManager.model;

import com.soulsarch.PasswordManager.model.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.URLINFORMATION_READ,Permission.URLINFORMATION_WRITE)),
    MODERATOR(Set.of(Permission.URLINFORMATION_READ, Permission.URLINFORMATION_WRITE, Permission.URLINFORMATION_MODERATE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
    }
}
