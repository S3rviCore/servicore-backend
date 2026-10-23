package com.servicore.web.dtos.response;

import java.math.BigDecimal;

public class MaterialResponseDTO {
    
    private final Long id;
    private final String materialName;
    private final String materialDescription;
    private final Long unitId;
    private final String unitName;
    private final BigDecimal stock;

    public MaterialResponseDTO(
        Long id,
        String materialName,
        String materialDescription,
        Long unitId,
        String unitName,
        BigDecimal stock
    ) {
        this.id = id;
        this.materialName = materialName;
        this.materialDescription = materialDescription;
        this.unitId = unitId;
        this.unitName = unitName;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public Long getUnitId() {
        return unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public BigDecimal getStock() {
        return stock;
    }

}
