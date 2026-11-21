package com.servicore.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.LoginRequestDTO;
import com.servicore.web.dtos.request.RegisterRequestDTO;
import com.servicore.web.dtos.response.LoginResponseDTO;
import com.servicore.web.dtos.response.RegisterResponseDTO;
import com.servicore.web.services.LoginService;
import com.servicore.web.services.RegistrationService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("api/auth")
@CrossOrigin 
public class AuthController {
    
    private final RegistrationService registrationService;
    private final LoginService loginService;

    public AuthController(RegistrationService registrationService, LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDTO register(@Valid @RequestBody RegisterRequestDTO dto) {
        return registrationService.register(dto);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return loginService.login(dto);
    }
    
}
