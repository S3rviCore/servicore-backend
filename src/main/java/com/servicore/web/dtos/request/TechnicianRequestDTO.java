package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TechnicianRequestDTO {
    
    @NotNull(message = "El usuario es requerido.")
    @Positive(message = "El ID del usuario debe ser positivo.")
    private Long userId;

    @NotBlank(message = "El numero de telefono es requerido")
    @Size(max = 16, message = "El numero no debe superar los 16 caracteres.")
    private String phone;

    @NotNull(message = "El estado es requerido")
    private Boolean status;

    public TechnicianRequestDTO() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
