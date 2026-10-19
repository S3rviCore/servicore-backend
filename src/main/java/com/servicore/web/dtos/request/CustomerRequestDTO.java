package com.servicore.web.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO {
    
    @NotNull(message = "El ID de la compania es requerido.")
    @Positive(message = "El ID de la compania debe ser positivo.")
    private Long companyId;

    @NotBlank(message = "El nombre del cliente es requerido.")
    @Size(max = 255, message = "El nombre del cliente no debe superar los 255 caracteres.")
    private String name;

    @NotBlank(message = "El apellido del cliente es requerido.")
    @Size(max = 255, message = "El apellido del cliente no debe superar los 255 caracteres.")
    private String lastName;

    @Size(max = 16, message = "El numero de telefono no debe contener mas de 16 caracteres")
    private String phone;

    @Email(message = "El email no esta en el formato valido.")
    @NotBlank(message = "El email es requerido.")
    @Size(max = 255, message = "El email no debe superar los 255 caracteres.")
    private String email;

    @NotNull(message = "El ID del pais es requerido.")
    @Positive(message = "El ID del pais debe ser positivo.")
    private Long countryId;

    @NotBlank(message = "La direccion es requerida.")
    @Size(max = 255, message = "La direccion no debe superar los 255 caracteres.")
    private String address;

    @NotNull(message = "El estado del cliente es requerido.")
    private Boolean status;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }   

    public void setStatus(Boolean status) {
        this.status = status;
    }

}