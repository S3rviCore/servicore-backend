package com.servicore.web.dtos.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ServiceRequestDTO {
    
    @NotNull(message = "El ID de la compania es requerido.")
    @Positive(message = "El ID de la compania es requerido.")
    private Long companyId;

    @NotBlank(message = "El nombre del servicio es requerido.")
    @Size(max = 255, message = "El nombre del servicio no debe superar los 255 caracteres.")
    private String name;

    @NotBlank(message = "La descripcion del servicio es requerida.")
    @Size(max = 255, message = "La descripcion no puede contener mas de 255 caracteres.")
    private String description;

    @NotNull(message = "El precio base es requerido.")
    @Positive(message = "El precio base del servicio es requerido.")
    private BigDecimal basePrecio;

    @NotNull(message = "El tiempo estimado en minutos es requerido.")
    @Positive(message = "El tiempo estimado en minutos debe ser positivo.")
    private Integer estimatedTimeMinutes;

    @NotNull(message = "El estado del servicio es requerido.")
    private Boolean status;

    public ServiceRequestDTO() {

    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBasePrice() {
        return basePrecio;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrecio = basePrice;
    }

    public Integer getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public void setEstimatedTimeMinutes(Integer estimatedTimeMinutes) {
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}