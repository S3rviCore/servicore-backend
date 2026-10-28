package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PaymentStatusRequestDTO {
    
    @NotBlank(message = "El nombre del estado es requerido.")
    @Size(max = 255, message = "El nombre no debe superar los 255 caracteres.")
    private String name;

    @NotBlank(message = "La descripcion del estado es requerido.")
    @Size(max = 255, message = "La descripcion del estado no debe superar los 255 caracteres.")
    private String description;

    public PaymentStatusRequestDTO() {

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

}
