package com.servicore.web.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {

    @NotBlank(message = "El nombre es requerido.")
    @Size(max = 255, message = "El nombre no debe tener mas de 255 caracteres.")
    private String name;

    @NotBlank(message = "El apellido es requerido.")
    @Size(max = 255, message = "El apellido no debe tener mas de 255 caracteres.")
    private String lastName;

    @NotBlank(message = "El email es requerido.")
    @Email(message = "El email no tiene un formato valido.")
    @Size(max = 255, message = "El email no debe tener mas de 255 caracteres.")
    private String email;

    @NotBlank(message = "La password es requerida.")
    @Size(min = 6, max = 255, message = "La password debe contener entre 6 y 255 caracteres.")
    private String password;

    @NotBlank(message = "El nombre de la compania es requerido.")
    @Size(max = 255, message = "El nombre de la compania no debe tener mas de 255 caracteres.")
    private String companyName;

    @NotBlank(message = "La descripcion de la compania es requerida.")
    @Size(max = 500, message = "La descripcion no debe tener mas de 500 caracteres.")
    private String companyDescription;

    @Email(message = "El email de la compania no tiene un formato valido.")
    private String companyEmail;

    @NotNull(message = "El pais es requerido.") 
    private Long countryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

}
