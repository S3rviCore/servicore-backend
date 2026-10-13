package com.servicore.web.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity 
@Table(
    name = "work_orders",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_work_order_company_order",
            columnNames = { "company_id", "order_code" }
        )
    }
)
public class WorkOrder {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String orderCode;
    
    @ManyToOne 
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne 
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    private LocalDateTime programmedDate;
    private String serviceAddress;
    private String problemDescription;

    @ManyToOne 
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    @ManyToOne 
    @JoinColumn(name = "status_id", nullable = false)
    private WorkOrderStatus workOrderStatus;

    @Column(precision = 12, scale = 2)
    private BigDecimal estimatedPrice;

    @Column(precision = 12, scale = 2)
    private BigDecimal finalPrice;

    @CreationTimestamp 
    private LocalDateTime createdAt;

    @UpdateTimestamp 
    private LocalDateTime updatedAt;

    private LocalDateTime finishedAt;

    public WorkOrder() {

    }

    public WorkOrder(
        Company company,
        String orderCode,
        Customer customer,
        Service service,
        LocalDateTime programmedDate,
        String serviceAddress,
        String problemDescription,
        Priority priority,
        WorkOrderStatus workOrderStatus,
        BigDecimal estimatedPrice
    ) {
        this.company = company;
        this.orderCode = orderCode;
        this.customer = customer;
        this.service = service;
        this.programmedDate = programmedDate;
        this.serviceAddress = serviceAddress;
        this.problemDescription = problemDescription;
        this.priority = priority;
        this.workOrderStatus = workOrderStatus;
        this.estimatedPrice = estimatedPrice;
    }

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public LocalDateTime getProgrammedDate() {
        return programmedDate;
    }

    public void setProgrammedDate(LocalDateTime programmedDate) {
        this.programmedDate = programmedDate;
    } 

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public WorkOrderStatus getWorkOrderStatus() {
        return workOrderStatus;
    }

    public void setWorkOrderStatus(WorkOrderStatus workOrderStatus) {
        this.workOrderStatus = workOrderStatus;
    }

    public BigDecimal getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(BigDecimal estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

}