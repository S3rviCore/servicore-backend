package com.servicore.web.dtos.response;

public class PaymentMethodResponseDTO {
    
    private final Long companyId;
    private final String companyName;
    private final String name;
    private final String description;

    public PaymentMethodResponseDTO(
        Long companyId,
        String companyName,
        String name,
        String description
    ) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.name = name;
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
