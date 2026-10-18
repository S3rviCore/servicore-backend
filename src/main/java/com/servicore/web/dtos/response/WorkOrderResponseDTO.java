package com.servicore.web.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WorkOrderResponseDTO {
    
    private final Long workOrderId;
    private final Long companyId;
    private final String companyName;
    private final String orderCode;
    private final Long customerId;
    private final String customerName;
    private final String customerLastName;
    private final Long serviceId;
    private final String serviceName;
    private final LocalDateTime programmedDate;
    private final String serviceAddress;
    private final String problemDescription;
    private final Long priorityId;
    private final String priorityName;
    private final Long workOrderStatusId;
    private final String workOrderStatusName;
    private final BigDecimal estimatedPrice;
    private final BigDecimal finalPrice;
    private final LocalDateTime finishedAt;

    public WorkOrderResponseDTO(
        Long workOrderId,
        Long companyId,
        String companyName,
        String orderCode,
        Long customerId,
        String customerName,
        String customerLastName,
        Long serviceId,
        String serviceName,
        LocalDateTime programmedDate,
        String serviceAddress,
        String problemDescription,
        Long priorityId,
        String priorityName,
        Long workOrderStatusId,
        String workOrderStatusName,
        BigDecimal estimatedPrice,
        BigDecimal finalPrice,
        LocalDateTime finishedAt
    ) {
        this.workOrderId = workOrderId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerLastName = customerLastName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.programmedDate = programmedDate;
        this.serviceAddress = serviceAddress;
        this.problemDescription = problemDescription;
        this.priorityId = priorityId;
        this.priorityName = priorityName;
        this.workOrderStatusId = workOrderStatusId;
        this.workOrderStatusName = workOrderStatusName;
        this.estimatedPrice = estimatedPrice;
        this.finalPrice = finalPrice;
        this.finishedAt = finishedAt;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public LocalDateTime getProgrammedDate() {
        return programmedDate;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public Long getWorkOrderStatusId() {
        return workOrderStatusId;
    }

    public String getWorkOrderStatusName() {
        return workOrderStatusName;
    }

    public BigDecimal getEstimatedPrice() {
        return estimatedPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

}
