package com.servicore.web.dtos.response;

public class RegisterResponseDTO {
    
    private Long userId;
    private String name;
    private String lastName;
    private String email;

    private Long companyId;
    private String companyName;
    private String companyDescription;
    private String companyEmail;
    private String role;
    
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

    public String getRole() {
        return role;
    }

}
