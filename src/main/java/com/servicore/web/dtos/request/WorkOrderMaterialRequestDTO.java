package com.servicore.web.dtos.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WorkOrderMaterialRequestDTO {
    
    @NotNull(message = "El ID de la orden es requerido.")
    @Positive(message = "El ID debe ser positivo.")
    private Long workOrderId;

    @NotNull(message = "El ID del material es requerido.")
    @Positive(message = "El ID del material es requerido.")
    private Long materialId;

    @NotNull(message = "La cantidad de material es requerida.")
    @Positive(message = "La cantidad de material es requerida.")
    private BigDecimal quantity;

    public WorkOrderMaterialRequestDTO() {

    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
