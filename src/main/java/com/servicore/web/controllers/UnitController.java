package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.UnitRequestDTO;
import com.servicore.web.dtos.response.UnitResponseDTO;
import com.servicore.web.services.UnitService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/units")
@CrossOrigin 
public class UnitController {
    
    private UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping 
    public List<UnitResponseDTO> getAllUnits() {
        return unitService.getAllUnits();
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public UnitResponseDTO createUnit(@Valid @RequestBody UnitRequestDTO dto) {
        return unitService.createUnit(dto);
    }

    @PutMapping("/{unitId}")
    public UnitResponseDTO updateUnit(
        @PathVariable Long unitId,
        @RequestBody UnitRequestDTO dto
    ) {
        return unitService.createUnit(dto);
    }

}
