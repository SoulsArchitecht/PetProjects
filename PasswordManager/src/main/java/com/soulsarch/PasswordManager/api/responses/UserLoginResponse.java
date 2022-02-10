package com.soulsarch.PasswordManager.api.responses;

import lombok.Data;

@Data
public class UserLoginResponse {
    private int id;
    private String name;
    private String email;
    private boolean moderation;
    private int moderationCount;
    private boolean settings;

}
