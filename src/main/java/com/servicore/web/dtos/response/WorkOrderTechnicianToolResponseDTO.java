package com.servicore.web.dtos.response;

import java.time.LocalDate;

public class WorkOrderTechnicianToolResponseDTO {

    private final Long toolId;
    private final String toolName;
    private final LocalDate usedAt;
    private final String conditionBefore;
    private final String conditionAfter;

    public WorkOrderTechnicianToolResponseDTO(
            Long toolId,
            String toolName,
            LocalDate usedAt,
            String conditionBefore,
            String conditionAfter) {
        this.toolId = toolId;
        this.toolName = toolName;
        this.usedAt = usedAt;
        this.conditionBefore = conditionBefore;
        this.conditionAfter = conditionAfter;
    }

    public Long getToolId() {
        return toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public LocalDate getUsedAt() {
        return usedAt;
    }

    public String getConditionBefore() {
        return conditionBefore;
    }

    public String getConditionAfter() {
        return conditionAfter;
    }
}