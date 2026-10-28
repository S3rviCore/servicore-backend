package com.servicore.web.dtos.response;

import java.math.BigDecimal;

public class WorkOrderMaterialResponseDTO {
    
    private final Long id;
    private final Long workOrderId;
    private final Long materialId;
    private final BigDecimal quantity;

    public WorkOrderMaterialResponseDTO(
        Long id,
        Long workOrderId,
        Long materialId,
        BigDecimal quantity
    ) {
        this.id = id;
        this.workOrderId = workOrderId;
        this.materialId = materialId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

}
