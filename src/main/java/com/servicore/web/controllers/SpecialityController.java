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

import com.servicore.web.dtos.request.SpecialityRequestDTO;
import com.servicore.web.dtos.response.SpecialityResponseDTO;
import com.servicore.web.services.SpecialityService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/specialities")
@CrossOrigin 
public class SpecialityController {
    
    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping 
    public List<SpecialityResponseDTO> getAllSpecialities() {
        return specialityService.getAllSpecialities();
    }

    @GetMapping("/{companyId}")
    public List<SpecialityResponseDTO> getAllSpecialitiesByCompanyId(@PathVariable Long companyId) {
        return specialityService.getAllSpecialitiesByCompanyId(companyId);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialityResponseDTO createSpeciality(@Valid @RequestBody SpecialityRequestDTO dto) {
        return specialityService.createSpeciality(dto);
    }

    @PutMapping("/{specialityId}")
    public SpecialityResponseDTO updateSpeciality(
        @PathVariable Long specialityId,
        @RequestBody SpecialityRequestDTO dto
    ) {
        return specialityService.updateSpeciality(specialityId, dto);
    }

}
