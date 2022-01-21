package com.soulsarch.PasswordManager.controller;

import com.soulsarch.PasswordManager.model.ErrorResponse;
import com.soulsarch.PasswordManager.model.TokenResponse;
import com.soulsarch.PasswordManager.model.User;
import com.soulsarch.PasswordManager.exception_handling.RegistrationException;
import com.soulsarch.PasswordManager.service.ClientService;
import com.soulsarch.PasswordManager.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        clientService.register(user.getClientId(), user.getClientSecret());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user) {
        clientService.checkCredentials(
                user.getClientId(), user.getClientSecret());
        return new TokenResponse(
                tokenService.generateToken(user.getClientId()));
    }

    @ExceptionHandler ({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handlerUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }

}
