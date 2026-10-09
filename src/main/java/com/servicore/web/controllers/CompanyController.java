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

import com.servicore.web.dtos.request.CompanyRequestDTO;
import com.servicore.web.dtos.response.CompanyResponseDTO;
import com.servicore.web.services.CompanyService;

import jakarta.validation.Valid;

@RestController  
@RequestMapping("/api/companies")
@CrossOrigin 
public class CompanyController {
    
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping 
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDTO createCompany(@Valid @RequestBody  CompanyRequestDTO dto) {
        return companyService.createCompany(dto);
    }

    @PutMapping("/{id}")
    public CompanyResponseDTO updateCompany(
        @PathVariable Long id,
        @Valid @RequestBody CompanyRequestDTO dto
    ) {
        return companyService.updateCompany(id, dto);
    }


}
