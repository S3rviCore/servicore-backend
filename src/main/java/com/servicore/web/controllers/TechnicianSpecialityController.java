package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.TechnicianSpecialityRequestDTO;
import com.servicore.web.dtos.response.TechnicianSpecialityResponseDTO;
import com.servicore.web.services.TechnicianSpecialityService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/technician-specialities")
@CrossOrigin 
public class TechnicianSpecialityController {
    
    private final TechnicianSpecialityService technicianSpecialityService;

    public TechnicianSpecialityController(TechnicianSpecialityService technicianSpecialityService) {
        this.technicianSpecialityService = technicianSpecialityService;
    }

    @GetMapping 
    public List<TechnicianSpecialityResponseDTO> getAllTechnicianSpecialities() {
        return technicianSpecialityService.getAllTechnicianSpecialities();
    }

    @GetMapping("/{companyId}")
    public List<TechnicianSpecialityResponseDTO> getAllTechnicianSpecialitiesByCompanyId(@PathVariable Long companyId) {
        return technicianSpecialityService.getAllTechnicianSpecialitiesByCompanyId(companyId);
    }

    @PostMapping("/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TechnicianSpecialityResponseDTO assignSpeciality(
        @PathVariable Long companyId,
        @Valid @RequestBody TechnicianSpecialityRequestDTO dto
    ) {
        return technicianSpecialityService.assignSpeciality(companyId, dto);
    }

    @DeleteMapping("/{companyId}/{technicianId}/{specialityId}")
    public void removeSpeciality(
        @PathVariable Long companyId,
        @PathVariable Long technicianId,
        @PathVariable Long specialityId
    ) {
        technicianSpecialityService.removeSpeciality(companyId, technicianId, specialityId);
    }

}
