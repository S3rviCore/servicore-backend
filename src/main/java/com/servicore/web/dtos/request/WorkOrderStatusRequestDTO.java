package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class WorkOrderStatusRequestDTO {
 
    @NotBlank(message = "El nombre del estatus es requerido.")
    @Size(max = 255, message = "El nombre del estatus no debe superar los 255 caracteres.")
    private String name;

    public WorkOrderStatusRequestDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
