package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PriorityRequestDTO {
    
    @NotBlank(message = "El nombre de la prioridad es requerido.")
    @Size(max = 255, message = "El nombre no debe superar los 255 caracteres.")
    private String name;

    public PriorityRequestDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}