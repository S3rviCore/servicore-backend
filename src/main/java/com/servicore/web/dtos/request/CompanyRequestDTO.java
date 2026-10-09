package com.servicore.web.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CompanyRequestDTO {
    
    private String logoUrl;

    @NotBlank(message = "El nombre de la compania es requerido.")
    @Size(max = 255, message = "El nombre de la compania no debe contener mas de 255 caracteres.")
    private String name;

    @Email(message = "El email no esta en el formato valido.")
    @Size(max = 255, message = "El email no debe superar los 255 caracteres.")
    private String email;

    @NotBlank(message = "La descripcion es requerida.")
    @Size(max = 255, message = "La descripcion no debe superar los 255 caracteres.")
    private String description;

    @NotNull(message = "El pais es requerido")
    private Long countryId;

    public CompanyRequestDTO() {

    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

}
