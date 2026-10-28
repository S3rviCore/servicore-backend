package com.servicore.web.dtos.response;

public class UnitResponseDTO {

    private final String name;
    private final String description;

    public UnitResponseDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
