package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RoleRequestDTO {
    
    @NotBlank(message = "El nombre del rol es requerido.")
    @Size(max = 255, message = "El nombre del rol no puede superar los 255 caracteres.")
    private String name;

    @NotBlank(message = "La descripcion del rol es necesaria.")
    @Size(max = 255, message = "La descripcion no puede superar los 255 caracteres.")
    private String description;

    @NotNull(message = "La compania es requerida.")
    private Long companyId;

    public RoleRequestDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}