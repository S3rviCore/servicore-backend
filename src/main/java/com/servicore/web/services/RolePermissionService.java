package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.RolePermissionRequestDTO;
import com.servicore.web.dtos.response.RolePermissionResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Permission;
import com.servicore.web.models.Role;
import com.servicore.web.models.RolePermission;
import com.servicore.web.models.RolePermissionId;
import com.servicore.web.repositories.PermissionRepository;
import com.servicore.web.repositories.RolePermissionRepository;
import com.servicore.web.repositories.RoleRepository;

@Service 
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    
    public RolePermissionService(
        RolePermissionRepository rolePermissionRepository,
        RoleRepository roleRepository,
        PermissionRepository permissionRepository
    ) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional(readOnly = true)
    public List<RolePermissionResponseDTO> getAllRolePermissions() {
        return rolePermissionRepository
                .findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional 
    public List<RolePermissionResponseDTO> getAllRolePermissionsByRoleId(Long roleId) {
        
        if (!roleRepository.existsById(roleId)) {
            throw new ResourceNotFoundException("Ese rol no fue encontrado.");
        }

        return rolePermissionRepository
                .findByRole_Id(roleId)
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    @Transactional 
    public RolePermissionResponseDTO assignPermission(RolePermissionRequestDTO dto) {

        Role role = roleRepository
            .findById(dto.getRoleId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Rol no encontrado")
            );

        Permission permission = permissionRepository
                    .findById(dto.getPermissionId())
                    .orElseThrow(() -> 
                        new ResourceNotFoundException("Permiso no encontrado")
                    );

        boolean alreadyAssigned = 
                    rolePermissionRepository.existsByRole_IdAndPermission_Id(
                        dto.getRoleId(), 
                        dto.getPermissionId()
                    );

        if (alreadyAssigned) {
            throw new IllegalArgumentException(
                "Este permiso ya esta asignado al rol."
            );
        }

        RolePermission rolePermission = new RolePermission(role, permission);

        RolePermission saved = rolePermissionRepository.save(rolePermission);

        return toResponseDTO(saved);

    }

    @Transactional 
    public void removePermission(Long roleId, Long permissionId) {

        RolePermission rolePermission = rolePermissionRepository
                    .findById(
                        new RolePermissionId(
                            roleId, 
                            permissionId
                        )
                    )
                    .orElseThrow(() ->
                        new ResourceNotFoundException("La relacion entre el rol y el permiso no existe.")
                    );

        rolePermissionRepository.delete(rolePermission);
    }

    public RolePermissionResponseDTO toResponseDTO(RolePermission rolePermission) {
        return new RolePermissionResponseDTO(
            rolePermission.getRole().getId(), 
            rolePermission.getRole().getName(), 
            rolePermission.getPermission().getId(), 
            rolePermission.getPermission().getName()
        );
    }

}
