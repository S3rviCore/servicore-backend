package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.SpecialityRequestDTO;
import com.servicore.web.dtos.response.SpecialityResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Speciality;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.SpecialityRepository;

@Service 
public class SpecialityService {
    
    private final SpecialityRepository specialityRepository;
    private final CompanyRepository companyRepository;

    public SpecialityService (
        SpecialityRepository specialityRepository,
        CompanyRepository companyRepository
    ) {
        this.specialityRepository = specialityRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<SpecialityResponseDTO> getAllSpecialities() {
        
        return specialityRepository
            .findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();

    }

    @Transactional(readOnly = true)
    public List<SpecialityResponseDTO> getAllSpecialitiesByCompanyId(Long companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Compania no encontrada.");
        }

        return  specialityRepository
                    .findByCompany_Id(companyId)
                    .stream()
                    .map(this::toResponseDTO)
                    .toList();

    }

    @Transactional 
    public SpecialityResponseDTO createSpeciality(SpecialityRequestDTO dto) {
        
        Company company = companyRepository
            .findById(dto.getCompanyId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Compania no encontrada.")
            );

        Speciality speciality = new Speciality(
            dto.getName(), 
            dto.getDescription(), 
            company
        );

        Speciality saved = specialityRepository.save(speciality);

        return toResponseDTO(saved);
    }

    @Transactional 
    public SpecialityResponseDTO updateSpeciality(
        Long specialityId,
        SpecialityRequestDTO dto
    ) {

        Speciality speciality = specialityRepository
            .findById(specialityId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Especialidad no encontrada.")
            );

        speciality.setName(dto.getName());
        speciality.setDescription(dto.getDescription());

        return toResponseDTO(speciality);
    } 

    public SpecialityResponseDTO toResponseDTO(Speciality speciality) {
        return new SpecialityResponseDTO(
            speciality.getId(), 
            speciality.getName(), 
            speciality.getDescription(), 
            speciality.getCompany().getId(),
            speciality.getCompany().getName()
        );
    }

}
