package com.servicore.web.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable 
public class TechnicianSpecialityId implements Serializable {
    
    private Long technicianId;
    private Long specialityId;

    public TechnicianSpecialityId() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TechnicianSpecialityId)) return false;

        TechnicianSpecialityId that = (TechnicianSpecialityId) o;

        return Objects.equals(technicianId, that.technicianId)
                && Objects.equals(specialityId, that.specialityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicianId, specialityId);
    }

}
