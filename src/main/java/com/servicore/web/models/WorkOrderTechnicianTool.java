package com.servicore.web.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "work_order_technician_tools")
public class WorkOrderTechnicianTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_order_technician_id", nullable = false)
    private WorkOrderTechnician workOrderTechnician;

    @ManyToOne
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    private LocalDate usedAt;

    private String conditionBefore;

    private String conditionAfter;

    public WorkOrderTechnicianTool() {
    }

    public WorkOrderTechnicianTool(
            WorkOrderTechnician workOrderTechnician,
            Tool tool,
            LocalDate usedAt,
            String conditionBefore) {
        this.workOrderTechnician = workOrderTechnician;
        this.tool = tool;
        this.usedAt = usedAt;
        this.conditionBefore = conditionBefore;
    }

    public Long getId() {
        return id;
    }

    public WorkOrderTechnician getWorkOrderTechnician() {
        return workOrderTechnician;
    }

    public void setWorkOrderTechnician(
            WorkOrderTechnician workOrderTechnician) {
        this.workOrderTechnician = workOrderTechnician;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public LocalDate getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDate usedAt) {
        this.usedAt = usedAt;
    }

    public String getConditionBefore() {
        return conditionBefore;
    }

    public void setConditionBefore(String conditionBefore) {
        this.conditionBefore = conditionBefore;
    }

    public String getConditionAfter() {
        return conditionAfter;
    }

    public void setConditionAfter(String conditionAfter) {
        this.conditionAfter = conditionAfter;
    }
}