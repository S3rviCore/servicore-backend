package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.MaterialRequestDTO;
import com.servicore.web.dtos.response.MaterialResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Material;
import com.servicore.web.models.Unit;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.MaterialRepository;
import com.servicore.web.repositories.UnitRepository;

@Service 
public class MaterialService {
    
    private MaterialRepository materialRepository;
    public UnitRepository unitRepository;
    private CompanyRepository companyRepository;

    public MaterialService(
        MaterialRepository materialRepository,
        UnitRepository unitRepository,
        CompanyRepository companyRepository
    ) {
        this.materialRepository = materialRepository;
        this.unitRepository = unitRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<MaterialResponseDTO> getAllMaterialsByCompanyId(Long companyId) {
        return materialRepository
            .findByCompany_Id(companyId)
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    @Transactional 
    public MaterialResponseDTO createMaterial(
        MaterialRequestDTO dto
    ) {

        Company company = companyRepository
            .findById(dto.getCompanyId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Compania no encontrada.")
            );

        Unit unit = unitRepository
            .findById(dto.getUnitId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Unidad no enontrada.")
            );

        Material material = new Material(
            dto.getName(), 
            dto.getDescription(), 
            unit, 
            dto.getStock(),
            company
        );

        Material saved = materialRepository.save(material);
        return toResponseDTO(saved);
    }

    public MaterialResponseDTO toResponseDTO(Material material) {
        return new MaterialResponseDTO(
            material.getId(), 
            material.getName(), 
            material.getDescription(), 
            material.getUnit().getId(),
            material.getUnit().getName(), 
            material.getStock()
        );
    }

}
