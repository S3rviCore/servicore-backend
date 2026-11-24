package com.servicore.web.services;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.servicore.web.dtos.request.LoginRequestDTO;
import com.servicore.web.dtos.response.LoginResponseDTO;
import com.servicore.web.models.User;
import com.servicore.web.repositories.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final long expirationMinutes;

    public LoginService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtEncoder jwtEncoder,
        @Value("${app.jwt.expiration-minutes:480}") long expirationMinutes
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.expirationMinutes = expirationMinutes;
    }

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail().trim().toLowerCase())
            .orElseThrow(() -> invalidCredentials());

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw invalidCredentials();
        }

        if (!Boolean.TRUE.equals(user.getStatus())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Tu cuenta esta desactivada.");
        }

        user.setLastAccess(LocalDateTime.now());

        return new LoginResponseDTO(
            generateToken(user),
            user.getId(),
            user.getName(),
            user.getLastName(),
            user.getEmail(),
            user.getRole().getName(),
            user.getCompany().getId(),
            Boolean.TRUE.equals(user.getMustChangePassword())
        );
    }

    private String generateToken(User user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("servicore")
            .issuedAt(now)
            .expiresAt(now.plus(Duration.ofMinutes(expirationMinutes)))
            .subject(user.getId().toString())
            .claim("email", user.getEmail())
            .claim("companyId", user.getCompany().getId())
            .claim("role", user.getRole().getName())
            .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    private ResponseStatusException invalidCredentials() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contrasena incorrectos.");
    }

}