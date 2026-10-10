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

import com.servicore.web.dtos.request.PermissionRequestDTO;
import com.servicore.web.dtos.response.PermissionResponseDTO;
import com.servicore.web.services.PermissionService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/permissions")
@CrossOrigin 
public class PermissionController {
    
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping 
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponseDTO createPermission(@Valid @RequestBody PermissionRequestDTO dto) {
        return permissionService.createPermission(dto);
    }

    @PutMapping("/{id}")
    public PermissionResponseDTO updatePermission(
        @PathVariable Long id,
        @Valid @RequestBody PermissionRequestDTO dto
    ) {
        return permissionService.updatePermission(id, dto);
    }

}
