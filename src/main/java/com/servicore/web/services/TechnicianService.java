package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.TechnicianRequestDTO;
import com.servicore.web.dtos.response.TechnicianResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Technician;
import com.servicore.web.models.User;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.TechnicianRepository;
import com.servicore.web.repositories.UserRepository;

@Service 
public class TechnicianService {
    
    private final TechnicianRepository technicianRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public TechnicianService(
        TechnicianRepository technicianRepository,
        UserRepository userRepository,
        CompanyRepository companyRepository
    ) {
        this.technicianRepository = technicianRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true) 
    public List<TechnicianResponseDTO> getAllTechnicians() {
        return technicianRepository
                .findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    @Transactional(readOnly = true)
    public List<TechnicianResponseDTO> getAllTechniciansByCompanyId(Long companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Compania no encontrada.");
        }

        return technicianRepository
                .findByUser_Company_Id(companyId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional 
    public TechnicianResponseDTO createTechnician(TechnicianRequestDTO dto) {

        User user = userRepository
            .findById(dto.getUserId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Usuario no encontrado.")
            );

        if (technicianRepository.existsByUser_Id(dto.getUserId())) {
            throw new IllegalArgumentException("Este usuario ya tiene un registro como tecnico.");
        }

        Technician technician = new Technician(
            user, 
            dto.getPhone(), 
            dto.getStatus()
        );

        Technician saved = technicianRepository.save(technician);

        return toResponseDTO(saved);
    }

    @Transactional 
    public TechnicianResponseDTO updateTechnician(
        Long technicianId,
        TechnicianRequestDTO dto
    ) {

        Technician technician = technicianRepository
            .findById(technicianId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Tecnico no encontrado.")
            );

        technician.setPhone(dto.getPhone());
        technician.setStatus(dto.getStatus());

        Technician saved = technicianRepository.save(technician);
        return toResponseDTO(saved);

    }

    public TechnicianResponseDTO toResponseDTO(Technician technician) {
        return new TechnicianResponseDTO(
            technician.getId(), 
            technician.getUser().getId(), 
            technician.getUser().getName(), 
            technician.getPhone(), 
            technician.getStatus()
        );
    }

}
