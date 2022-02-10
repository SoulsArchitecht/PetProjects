package com.soulsarch.PasswordManager.controller;


import com.soulsarch.PasswordManager.api.responses.LoginResponse;
import com.soulsarch.PasswordManager.api.requests.LoginRequest;
import com.soulsarch.PasswordManager.api.responses.UserLoginResponse;
import com.soulsarch.PasswordManager.entity.User;
import com.soulsarch.PasswordManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth/")
public class ApiAuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public ApiAuthController(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(getLoginResponse(user.getName()));
    }

    @GetMapping("/check")
    public ResponseEntity<LoginResponse> check(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(new LoginResponse());
        }
        return ResponseEntity.ok(getLoginResponse(principal.getName()));
    }

    private LoginResponse getLoginResponse(String email) {
        com.soulsarch.PasswordManager.entity.User currentUser =
                userRepository.findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException(email));

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setEmail(currentUser.getEmail());
        userLoginResponse.setName(currentUser.getName());
        userLoginResponse.setModeration(currentUser.getModeratorTrue() == 1);
        userLoginResponse.setId(currentUser.getId());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setResult(true);
        loginResponse.setUserLoginResponse(userLoginResponse);

        return loginResponse;
    }
}
