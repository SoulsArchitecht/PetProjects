package com.soulsarch.PasswordManager.service;

public interface TokenService {
    String generateToken(String clientId);
}
