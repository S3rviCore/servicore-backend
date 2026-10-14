package com.servicore.web.dtos.response;

public class WorkOrderStatusResponseDTO {
    
    private final String name;

    public WorkOrderStatusResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
