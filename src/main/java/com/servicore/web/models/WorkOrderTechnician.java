package com.servicore.web.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.servicore.web.enums.TechnicianRole;
import com.servicore.web.enums.WorkOrderTechnicianStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "work_order_technicians")
public class WorkOrderTechnician {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "technician_id", nullable = false)
    private Technician technician;

    @ManyToOne 
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrder workOrder;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkOrderTechnicianStatus status = WorkOrderTechnicianStatus.WORKING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechnicianRole role;

    @CreationTimestamp 
    private LocalDateTime assignedAt;

    public WorkOrderTechnician() {

    }

    public WorkOrderTechnician(
        Technician technician,
        WorkOrder workOrder,
        TechnicianRole role
    ) {
        this.technician = technician;
        this.workOrder = workOrder;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public WorkOrderTechnicianStatus getStatus() {
        return status;
    }

    public void setStatus(WorkOrderTechnicianStatus status) {
        this.status = status;
    }

    public TechnicianRole getRole() {
        return role;
    }

    public void setRole(TechnicianRole role) {
        this.role = role;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

}