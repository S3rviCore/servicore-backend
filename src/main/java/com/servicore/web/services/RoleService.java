package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.RoleRequestDTO;
import com.servicore.web.dtos.response.RoleResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Role;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.RoleRepository;

@Service 
public class RoleService {
    
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;

    public RoleService(
        RoleRepository roleRepository,
        CompanyRepository companyRepository
    ) {
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<RoleResponseDTO> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    @Transactional(readOnly = true)
    public List<RoleResponseDTO> getAllRolesByCompanyId(Long companyId) {
        
        List<Role> roles = roleRepository.findByCompanyId(companyId);

        if (roles.isEmpty()) {
            throw new ResourceNotFoundException("Rol no encontrado en esa compania.");
        }

        return roles.stream()
                    .map(this::toResponseDTO)
                    .toList();

    }

    @Transactional 
    public RoleResponseDTO createRole(RoleRequestDTO dto) {

        Company company = companyRepository
                .findById(dto.getCompanyId())
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Compania no encontrada.")
                );

        Role role = new Role(
            dto.getName(), 
            dto.getDescription(), 
            company
        );

        Role roleSaved = roleRepository.save(role);

        return toResponseDTO(roleSaved);
    }

    @Transactional 
    public RoleResponseDTO updateRole(
        Long id,
        RoleRequestDTO dto
    ) { 
        
        Role role = roleRepository
                        .findByIdAndCompany_Id(id, dto.getCompanyId())
                        .orElseThrow(() -> 
                            new ResourceNotFoundException("Rol no encontrado.")
                        );
        
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        return toResponseDTO(role);
    }

    public RoleResponseDTO toResponseDTO(Role role) {
        return new RoleResponseDTO(
            role.getId(), 
            role.getName(), 
            role.getDescription(), 
            role.getCompany().getId(), 
            role.getCompany().getName()
        );
    }

}
