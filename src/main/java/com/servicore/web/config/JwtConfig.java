
package com.servicore.web.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

@Configuration
public class JwtConfig {

    @Value("${servicore.jwt.secret}")
    private String jwtSecret;

    private SecretKey secretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtSecret);

        if (decodedKey.length < 32) {
            throw new IllegalStateException(
                "La clave JWT debe tener al menos 32 bytes."
            );
        }

        return new SecretKeySpec(decodedKey, "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(
            new ImmutableSecret<>(secretKey())
        );
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
            .withSecretKey(secretKey())
            .macAlgorithm(MacAlgorithm.HS256)
            .build();
    }
}
