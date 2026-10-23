package com.servicore.web.dtos.response;

import java.util.List;
import com.servicore.web.enums.TechnicianRole;

public class WorkOrderTechnicianResponseDTO {

    private final Long technicianId;
    private final String technicianName;
    private final TechnicianRole technicianRole;
    private final List<WorkOrderTechnicianToolResponseDTO> tools;

    public WorkOrderTechnicianResponseDTO(
            Long technicianId,
            String technicianName,
            TechnicianRole technicianRole,
            List<WorkOrderTechnicianToolResponseDTO> tools) {
        this.technicianId = technicianId;
        this.technicianName = technicianName;
        this.technicianRole = technicianRole;
        this.tools = tools;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public TechnicianRole getTechnicianRole() {
        return technicianRole;
    }

    public List<WorkOrderTechnicianToolResponseDTO> getTools() {
        return tools;
    }
}