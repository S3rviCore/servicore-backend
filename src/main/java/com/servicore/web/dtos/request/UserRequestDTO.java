package com.servicore.web.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    
    @NotBlank(message = "El nombre de usuario es requerido.")
    @Size(max = 255, message = "El nombre debe contener menos de 255 caracteres.")
    private String name;

    @NotBlank(message = "El apellido del usuario es requerido.")
    @Size(max = 255, message = "El apellido debe contener menos de 255 caracteres")
    private String lastName;

    @Email(message = "El email no esta en el formato valido.")
    @NotBlank(message = "El email es requerido.")
    @Size(max = 255, message = "El email no debe superar los 255 caracteres.")
    private String email;

    @NotNull(message = "El role del usuario es requerido.")
    private Long roleId;

    public UserRequestDTO() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
