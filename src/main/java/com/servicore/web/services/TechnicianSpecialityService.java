package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.TechnicianSpecialityRequestDTO;
import com.servicore.web.dtos.response.TechnicianSpecialityResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Speciality;
import com.servicore.web.models.Technician;
import com.servicore.web.models.TechnicianSpecialities;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.SpecialityRepository;
import com.servicore.web.repositories.TechnicianRepository;
import com.servicore.web.repositories.TechnicianSpecialityRepository;

@Service 
public class TechnicianSpecialityService {
    
    private final TechnicianSpecialityRepository technicianSpecialityRepository;
    private final CompanyRepository companyRepository;
    private final TechnicianRepository technicianRepository;
    private final SpecialityRepository specialityRepository;

    public TechnicianSpecialityService(
        TechnicianSpecialityRepository technicianSpecialityRepository,
        CompanyRepository companyRepository,
        TechnicianRepository technicianRepository,
        SpecialityRepository specialityRepository
    ) {
        this.technicianSpecialityRepository = technicianSpecialityRepository;
        this.companyRepository = companyRepository;
        this.technicianRepository = technicianRepository;
        this.specialityRepository = specialityRepository;
    }

    @Transactional(readOnly = true)
    public List<TechnicianSpecialityResponseDTO> getAllTechnicianSpecialities() {
        
        return technicianSpecialityRepository
                .findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList(); 
    
    }

    @Transactional(readOnly = true)
    public List<TechnicianSpecialityResponseDTO> getAllTechnicianSpecialitiesByCompanyId(Long companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Compania no encontrada.");
        }

        return technicianSpecialityRepository
                .findByTechnician_User_Company_Id(companyId)
                .stream()
                .map(this::toResponseDTO)
                .toList();

    } 

    @Transactional 
    public TechnicianSpecialityResponseDTO assignSpeciality(
        Long companyId,
        TechnicianSpecialityRequestDTO dto
    ) {
        
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Compania no encontrada.");
        }

        Technician technician = technicianRepository
            .findByIdAndUser_Company_Id(dto.getTechnicianId(), companyId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Tecnico no encontrado.")
            );

        Speciality speciality = specialityRepository
            .findByIdAndCompany_Id(dto.getSpecialityId(), companyId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Especialidad no encontrada.")
            );
        
        if (technicianSpecialityRepository.existsByTechnician_IdAndSpeciality_Id(dto.getTechnicianId(), dto.getSpecialityId())) {
            throw new IllegalArgumentException("Esa especialidad ya esta asignada a ese tecnico.");
        }

        TechnicianSpecialities technicianSpecialities = new TechnicianSpecialities(technician, speciality);

        TechnicianSpecialities saved = technicianSpecialityRepository
            .save(technicianSpecialities);

        return toResponseDTO(saved);
    }

    
    @Transactional
    public void removeSpeciality(
        Long companyId,
        Long technicianId,
        Long specialityId
    ) {
        TechnicianSpecialities relation =
            technicianSpecialityRepository
                .findByTechnician_IdAndSpeciality_IdAndTechnician_User_Company_Id(
                    technicianId,
                    specialityId,
                    companyId
                )
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "La asignación no existe en esta compañía."
                    )
                );

        technicianSpecialityRepository.delete(relation);
    }


    public TechnicianSpecialityResponseDTO toResponseDTO(TechnicianSpecialities technicianSpecialities) {
        return new TechnicianSpecialityResponseDTO(
            technicianSpecialities.getTechnician().getId(), 
            technicianSpecialities.getTechnician().getUser().getName(), 
            technicianSpecialities.getSpeciality().getId(), 
            technicianSpecialities.getSpeciality().getName()
        );
    }

}
