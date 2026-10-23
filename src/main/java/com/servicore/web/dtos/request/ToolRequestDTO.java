package com.servicore.web.dtos.request;

import com.servicore.web.enums.ToolStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ToolRequestDTO {
    
    @NotBlank(message = "El nombre de la herramienta es requerido.")
    @Size(max = 255, message = "El nombre de la herramienta no debe superar los 255 caracteres.")
    private String name;

    @NotBlank(message = "El numero serial es requerido.")
    private String serialNumber;

    @NotNull(message = "El ID compania es requerido.")
    @Positive(message = "El ID de la compania debe ser positivo.")
    private Long companyId;

    @NotNull(message = "El estado de la herramienta es requerida.")
    private ToolStatus status;

    public ToolRequestDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public void setStatus(ToolStatus status) {
        this.status = status;
    }

}
