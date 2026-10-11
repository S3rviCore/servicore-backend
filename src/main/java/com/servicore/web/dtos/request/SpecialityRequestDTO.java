package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SpecialityRequestDTO {
    
    @NotBlank(message = "El nombre de la especialidad es requerida.")
    @Size(max = 255, message = "El nombre de la especialidad no puede superar los 255 caracteres.")
    private String name;
    
    @NotBlank(message = "La descripcion de la especialidad es requerida.")
    @Size(max = 255, message = "La descripcion no puede superar los 255 caracteres.")
    private String description;

    @NotNull(message = "El ID de la compania es requerido.")
    @Positive(message = "El ID de la compania debe ser positivo.")
    private Long companyId;

    public SpecialityRequestDTO() {

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