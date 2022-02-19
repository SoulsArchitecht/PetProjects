package com.soulsarch.PasswordManager.model;

public enum Permission {
    URLINFORMATION_READ("urlInformation:read"),
    URLINFORMATION_WRITE("urlInformation:write"),
    URLINFORMATION_MODERATE("urlInformation:moderate");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
