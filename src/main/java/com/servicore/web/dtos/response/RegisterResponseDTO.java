package com.servicore.web.dtos.response;

public class RegisterResponseDTO {

    private final Long userId;
    private final String name;
    private final String lastName;
    private final String email;

    private final Long companyId;
    private final String companyName;
    private final String companyDescription;
    private final String companyEmail;

    private final String roleName;

    private final String token;
    private final String tokenType;

    public RegisterResponseDTO(
            Long userId,
            String name,
            String lastName,
            String email,
            Long companyId,
            String companyName,
            String companyDescription,
            String companyEmail,
            String roleName,
            String token
    ) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.companyEmail = companyEmail;
        this.roleName = roleName;
        this.token = token;
        this.tokenType = "Bearer";
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

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }
}