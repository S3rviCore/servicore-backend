package com.servicore.web.dtos.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class MaterialRequestDTO {
    
    @NotBlank(message = "El nombre del material es requerido.")
    @Size(max = 255, message = "El nombre no debe contener mas de 255 caracteres.")
    private String name;

    @Size(max = 255, message = "La descripcion no debe sobrepasar los 255 caracteres.")
    private String description;

    @NotNull(message = "El ID de la unidad es requerido.")
    @Positive(message = "El ID de la unidad debe ser positivo.")    
    private Long unitId;

    @NotNull(message = "El ID de la compania es requerido.")
    @Positive(message = "El ID de la compania debe ser positivo.")
    private Long companyId;

    @NotNull(message = "El stock es requerido.")
    @Positive(message = "El stock debe ser positivo.")
    private BigDecimal stock;

    public MaterialRequestDTO() {

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

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

}
