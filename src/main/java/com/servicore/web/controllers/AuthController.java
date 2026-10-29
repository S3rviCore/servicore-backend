package com.servicore.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.LoginRequestDTO;
import com.servicore.web.dtos.response.LoginResponseDTO;
import com.servicore.web.services.AuthService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/auth")
@CrossOrigin 
public class AuthController {
    
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(
        @Valid @RequestBody LoginRequestDTO dto
    ) {
        return authService.login(dto);
    }

}
