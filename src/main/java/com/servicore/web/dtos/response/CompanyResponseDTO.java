package com.servicore.web.dtos.response;

public class CompanyResponseDTO {
    
    private final Long id;
    private final String logoUrl;
    private final String name;
    private final String email;
    private final String description;
    private final Long countryId;
    private final String countryName;

    public CompanyResponseDTO(
        Long id,
        String logoUrl,
        String name,
        String email,
        String description,
        Long countryId,
        String countryName
    ) {
        this.id = id;
        this.logoUrl = logoUrl;
        this.name = name;
        this.email = email;
        this.description = description;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

}
