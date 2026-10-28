package com.servicore.web.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "work_order_materials")
public class WorkOrderMaterial {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrder workOrder;

    @ManyToOne 
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    private BigDecimal quantity;

    public WorkOrderMaterial() {

    }

    public WorkOrderMaterial(
        WorkOrder workOrder,
        Material material,
        BigDecimal quantity
    ) {
        this.workOrder = workOrder;
        this.material = material;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public WorkOrder getWorkOder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

}
