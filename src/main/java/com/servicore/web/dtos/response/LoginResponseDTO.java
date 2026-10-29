
package com.servicore.web.dtos.response;

public class LoginResponseDTO {

    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private Long userId;
    private String name;
    private String lastName;
    private String email;
    private String role;

    public LoginResponseDTO(
        String accessToken,
        long expiresIn,
        Long userId,
        String name,
        String lastName,
        String email,
        String role
    ) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.expiresIn = expiresIn;
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
