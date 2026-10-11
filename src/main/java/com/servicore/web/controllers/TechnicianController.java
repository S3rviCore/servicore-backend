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

import com.servicore.web.dtos.request.TechnicianRequestDTO;
import com.servicore.web.dtos.response.TechnicianResponseDTO;
import com.servicore.web.services.TechnicianService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/technicians")
@CrossOrigin 
public class TechnicianController {
    
    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping 
    public List<TechnicianResponseDTO> getAllTechnicians() {
        return technicianService.getAllTechnicians();
    }

    @GetMapping("/{companyId}")
    public List<TechnicianResponseDTO> getAllTechniciansByCompanyId(@PathVariable Long companyId) {
        return technicianService.getAllTechniciansByCompanyId(companyId);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public TechnicianResponseDTO createTechnician(@Valid @RequestBody TechnicianRequestDTO dto) {
        return technicianService.createTechnician(dto);
    }

    @PutMapping("/{technicianId}")
    public TechnicianResponseDTO updateTechnician(
        @PathVariable Long technicianId,
        @Valid @RequestBody  TechnicianRequestDTO dto 
    ) {
        return technicianService.updateTechnician(technicianId, dto);
    }

}
