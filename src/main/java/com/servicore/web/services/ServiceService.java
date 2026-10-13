package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.ServiceRequestDTO;
import com.servicore.web.dtos.response.ServiceResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.ServiceModel;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.ServiceRepository;

@Service 
public class ServiceService {
    
    private final ServiceRepository serviceRepository;
    private final CompanyRepository companyRepository;

    public ServiceService(
        ServiceRepository serviceRepository,
        CompanyRepository companyRepository
    ) {
        this.serviceRepository = serviceRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<ServiceResponseDTO> getAllServices() {
        
        return serviceRepository
            .findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();

    }

    @Transactional(readOnly = true)
    public List<ServiceResponseDTO> getAllServicesByCompanyId(Long companyId) {
        
        return serviceRepository
            .findByCompany_Id(companyId)
            .stream()
            .map(this::toResponseDTO)
            .toList();
    
    }

    @Transactional 
    public ServiceResponseDTO createService(ServiceRequestDTO dto) {

        Company company = companyRepository
            .findById(dto.getCompanyId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Compania no encontrada.")  
            );

        ServiceModel service = new ServiceModel(
            company, 
            dto.getName(), 
            dto.getDescription(), 
            dto.getBasePrice(), 
            dto.getEstimatedTimeMinutes(), 
            dto.getStatus()
        );

        ServiceModel saved = serviceRepository.save(service);
        
        return toResponseDTO(saved);

    }

    @Transactional 
    public ServiceResponseDTO updateService(
        Long companyId,
        Long serviceId,
        ServiceRequestDTO dto
    ) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Compania no encontrada.");
        }

        ServiceModel service = serviceRepository
            .findById(serviceId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Servicio no encontrado.")
            );

        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setBasePrice(dto.getBasePrice());
        service.setEstimatedTime(dto.getEstimatedTimeMinutes());
        service.setStatus(dto.getStatus());

        return toResponseDTO(service);
    }

    public ServiceResponseDTO toResponseDTO(ServiceModel service) {
        return new ServiceResponseDTO(
            service.getId(), 
            service.getCompany().getId(), 
            service.getCompany().getName(), 
            service.getName(), 
            service.getDescription(), 
            service.getBasePrice(), 
            service.getEstimatedTime(), 
            service.getStatus()
        );
    }

}
