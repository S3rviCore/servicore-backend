package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.UnitRequestDTO;
import com.servicore.web.dtos.response.UnitResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Unit;
import com.servicore.web.repositories.UnitRepository;

@Service 
public class UnitService {
    
    private UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Transactional(readOnly = true)
    public List<UnitResponseDTO> getAllUnits() {
        return unitRepository
            .findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    @Transactional
    public UnitResponseDTO createUnit(UnitRequestDTO dto) {
        Unit unit = new Unit(dto.getName(), dto.getDescription());
        Unit saved = unitRepository.save(unit);

        return toResponseDTO(saved);
    }

    @Transactional 
    public UnitResponseDTO updateUnit(Long unitId, UnitRequestDTO dto) {
        Unit unit = unitRepository
            .findById(unitId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Unidad no encontrada.")
            );
        
        unit.setName(dto.getName());
        unit.setDescription(dto.getDescription());

        return toResponseDTO(unit);
    }

    public UnitResponseDTO toResponseDTO(Unit unit) {
        return new UnitResponseDTO(unit.getName(), unit.getDescription());
    }

}
