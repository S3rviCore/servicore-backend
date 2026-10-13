package com.servicore.web.dtos.response;

import java.math.BigDecimal;

public class ServiceResponseDTO {
    
    private final Long serviceId;
    private final Long companyId;
    private final String companyName;
    private final String serviceName;
    private final String serviceDescription;
    private final BigDecimal basePrice;
    private final Integer estimatedTimeMinutes;
    private final Boolean status;

    public ServiceResponseDTO(
        Long serviceId,
        Long companyId,
        String companyName,
        String serviceName,
        String serviceDescription,
        BigDecimal basePrice,
        Integer estimatedTimeMinutes,
        Boolean status
    ) {
        this.serviceId = serviceId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.basePrice = basePrice;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
        this.status = status;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public Integer getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public Boolean getStatus() {
        return status;
    }

}