package com.servicore.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.MaterialRequestDTO;
import com.servicore.web.dtos.response.MaterialResponseDTO;
import com.servicore.web.services.MaterialService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/materials")
@CrossOrigin 
public class MaterialController {
    
    private MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/{companyId}")
    public List<MaterialResponseDTO> getAllMaterialsByCompanyId(@PathVariable Long companyId) {
        return materialService.getAllMaterialsByCompanyId(companyId);
    }

    @PostMapping
    public MaterialResponseDTO createMaterial(
        @Valid @RequestBody MaterialRequestDTO dto
    ) {
        return materialService.createMaterial(dto);
    }   


}
