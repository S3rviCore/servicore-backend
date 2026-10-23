package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class WorkOrderTechnicianToolRequestDTO {

    @NotNull(message = "El ID de la herramienta es requerido.")
    @Positive(message = "El ID de la herramienta debe ser positivo.")
    private Long toolId;

    @Size(max = 255, message = "La condición inicial no debe superar los 255 caracteres.")
    private String conditionBefore;

    public WorkOrderTechnicianToolRequestDTO() {
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public String getConditionBefore() {
        return conditionBefore;
    }

    public void setConditionBefore(String conditionBefore) {
        this.conditionBefore = conditionBefore;
    }
}