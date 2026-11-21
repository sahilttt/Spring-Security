package com.securityexample.security.controller;

import com.securityexample.security.dto.CreateUserDTO;
import com.securityexample.security.dto.RegisterUserDto;
import com.securityexample.security.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserDto> signUp(@Valid @RequestBody CreateUserDTO createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUserDto(createUserDto));
    }
}
