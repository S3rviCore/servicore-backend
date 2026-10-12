package com.servicore.web.dtos.response;

public class TechnicianSpecialityResponseDTO {
    
    private final Long technicianId;
    private final String technicianName;
    private final Long specialityId;
    private final String specialityName;

    public TechnicianSpecialityResponseDTO (
        Long technicianId,
        String technicianName,
        Long specialityId,
        String specialityName
    ) {
        this.technicianId = technicianId;
        this.technicianName = technicianName;
        this.specialityId = specialityId;
        this.specialityName = specialityName;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public String getSpecialityName() {
        return specialityName;
    }

}
