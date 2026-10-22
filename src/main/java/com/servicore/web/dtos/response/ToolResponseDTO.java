package com.servicore.web.dtos.response;

import com.servicore.web.enums.ToolStatus;

public class ToolResponseDTO {
    
    private final Long id;
    private final String name;
    private final String serialNumber;
    private final ToolStatus status;
    private final Long companyId;
    private final String companyName;

    public ToolResponseDTO(
        Long id,
        String name,
        String serialNumber,
        ToolStatus status,
        Long companyId,
        String companyName
    ) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.status = status;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
 
}