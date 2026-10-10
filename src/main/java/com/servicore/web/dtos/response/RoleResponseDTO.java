package com.servicore.web.dtos.response;

public class RoleResponseDTO {
    
    private final Long id;
    private final String name;
    private final String description;
    private final Long companyId;
    private final String companyName;

    public RoleResponseDTO(
        Long id,
        String name,
        String description,
        Long companyId,
        String companyName
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

}
