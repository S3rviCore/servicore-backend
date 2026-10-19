
package com.servicore.web.dtos.request;

import com.servicore.web.enums.TechnicianRole;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WorkOrderTechnicinaRequestDTO {

    @NotNull(message = "El ID del técnico es requerido.")
    @Positive(message = "El ID del técnico debe ser positivo.")
    private Long technicianId;

    @NotNull(message = "El rol del técnico es requerido.")
    private TechnicianRole technicianRole;

    public WorkOrderTechnicinaRequestDTO() {
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public TechnicianRole getTechnicianRole() {
        return technicianRole;
    }

    public void setTechnicianRole(TechnicianRole technicianRole) {
        this.technicianRole = technicianRole;
    }
}
