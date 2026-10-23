package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UnitRequestDTO {
    
    @NotBlank(message = "El nombre de la unidad es requerida.")
    @Size(max = 255, message = "El nombre no debe superar los 255 caracteres.")
    private String name;

    @NotBlank(message = "La descripcion de la unidad es requerida.")
    @Size(max = 255, message = "La descripcion no debe superar los 255 caracteres.")
    private String description;

    public UnitRequestDTO() {

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
