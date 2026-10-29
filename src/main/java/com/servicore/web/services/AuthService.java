package com.servicore.web.services;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
public class AuthService {
    
    private static final Duration TOKEN_DURATION = Duration.ofMinutes(30);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public AuthService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtEncoder jwtEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @Transactional 
    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(this::invalidCredentials);

        if (user.getPassword() == null
            || !passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
            )) {
            throw invalidCredentials();
        }

        if (!Boolean.TRUE.equals(user.getStatus())) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "La cuenta está desactivada."
            );
        }

        Instant now = Instant.now();
        Instant expiresAt = now.plus(TOKEN_DURATION);

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("servicore-api")
            .issuedAt(now)
            .expiresAt(expiresAt)
            .subject(user.getId().toString())
            .claim("email", user.getEmail())
            .claim("name", user.getName())
            .claim("lastName", user.getLastName())
            .claim("role", user.getRole().getName())
            .claim("companyId", user.getCompany().getId())
            .claim(
                "mustChangePassword",
                Boolean.TRUE.equals(user.getMustChangePassword())
            )
            .build();

        String token = jwtEncoder.encode(
            JwtEncoderParameters.from(header, claims)
        ).getTokenValue();

        user.setLastAccess(LocalDateTime.ofInstant(now, ZoneOffset.UTC));
        userRepository.save(user);

        return new LoginResponseDTO(
            token,
            TOKEN_DURATION.toSeconds(),
            user.getId(),
            user.getName(),
            user.getLastName(),
            user.getEmail(),
            user.getRole().getName()
        );
    }

    private ResponseStatusException invalidCredentials() {
        return new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "Correo o contraseña incorrectos."
        );
    }

}
