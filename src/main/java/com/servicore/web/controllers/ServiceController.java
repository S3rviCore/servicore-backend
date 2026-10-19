package com.servicore.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.ServiceRequestDTO;
import com.servicore.web.dtos.response.ServiceResponseDTO;
import com.servicore.web.services.ServiceService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/services")
@CrossOrigin 
public class ServiceController {
    
    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping 
    public List<ServiceResponseDTO> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{companyId}")
    public List<ServiceResponseDTO> getAllServicesByCompanyId(@PathVariable Long companyId) {
        return serviceService.getAllServicesByCompanyId(companyId);
    }

    @PostMapping 
    public ServiceResponseDTO createService(@RequestBody ServiceRequestDTO dto) {
        return serviceService.createService(dto);
    }

    @PutMapping("/{companyId}/{serviceId}")
    public ServiceResponseDTO updateService(
        @PathVariable Long companyId,
        @PathVariable Long serviceId,
        @Valid @RequestBody ServiceRequestDTO dto
    ) {
        return serviceService.updateService(companyId, serviceId, dto);
    }

}
