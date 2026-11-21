package com.servicore.web.dtos.response;

public class LoginResponseDTO {

    private final String accessToken;
    private final String tokenType = "Bearer";
    private final Long userId;
    private final String name;
    private final String lastName;
    private final String email;
    private final String role;
    private final Long companyId;
    private final boolean mustChangePassword;

    public LoginResponseDTO(
        String accessToken,
        Long userId,
        String name,
        String lastName,
        String email,
        String role,
        Long companyId,
        boolean mustChangePassword
    ) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.companyId = companyId;
        this.mustChangePassword = mustChangePassword;
    }

    public String getAccessToken() { 
        return accessToken; 
    }

    public String getTokenType() { 
        return tokenType; 
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

    public Long getCompanyId() { 
        return companyId; 
    }

    public boolean isMustChangePassword() { 
        return mustChangePassword; 
    }

}