package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TechnicianSpecialityRequestDTO {
 
    @NotNull(message = "El ID del tecnico es requerido.")
    @Positive(message = "El ID del tecnico debe ser positivo.")
    private Long technicianId;

    @NotNull(message = "El ID de la especialidad es requerido.")
    @Positive(message = "El ID  de la especialidad debe ser positivo.")
    private Long specialityId;

    public Long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }

}
