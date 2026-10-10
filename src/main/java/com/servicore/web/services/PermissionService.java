package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.PermissionRequestDTO;
import com.servicore.web.dtos.response.PermissionResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Permission;
import com.servicore.web.repositories.PermissionRepository;

@Service 
public class PermissionService {
    
    private PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional(readOnly = true)
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionRepository
            .findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    @Transactional 
    public PermissionResponseDTO createPermission(PermissionRequestDTO dto) {

        Permission permission = new Permission(
            dto.getName(), 
            dto.getDescription()
        );

        Permission permissionSaved = permissionRepository.save(permission);

        return toResponseDTO(permissionSaved);
    }

    @Transactional 
    public PermissionResponseDTO updatePermission(Long id, PermissionRequestDTO dto) {
        Permission permission = permissionRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Permiso no encontrado.")
            );

        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());

        Permission permissionSaved = permissionRepository.save(permission);

        return toResponseDTO(permissionSaved);
    }

    public PermissionResponseDTO toResponseDTO(Permission permission) {
        return new PermissionResponseDTO(
            permission.getId(), 
            permission.getName(), 
            permission.getDescription()
        );
    }

}
