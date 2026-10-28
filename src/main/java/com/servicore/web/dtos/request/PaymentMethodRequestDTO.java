package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentMethodRequestDTO {

    @NotNull(message = "El ID de la compania es requerido.")
    @Positive(message = "El ID de la compania debe ser positivo.")
    private Long companyId;
    
    @NotBlank(message = "El nombre del metodo de pago es requerido.")
    private String name;

    @NotBlank(message = "La descripcion del metodo de pago es requerida.")
    private String description;

    public PaymentMethodRequestDTO() {

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

}
