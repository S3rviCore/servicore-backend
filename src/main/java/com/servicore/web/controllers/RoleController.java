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

import com.servicore.web.dtos.request.RoleRequestDTO;
import com.servicore.web.dtos.response.RoleResponseDTO;
import com.servicore.web.services.RoleService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/roles")
@CrossOrigin 
public class RoleController {
    
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping 
    public List<RoleResponseDTO> getAllRole() {
        return roleService.getAllRoles();
    }

    @GetMapping("/company/{companyId}")
    public List<RoleResponseDTO> getAllRolesByCompanyId(@PathVariable  Long companyId) {
        return roleService.getAllRolesByCompanyId(companyId);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDTO createRole(@Valid @RequestBody  RoleRequestDTO dto) {
        return roleService.createRole(dto);
    }

    @PutMapping("/{id}") 
    public RoleResponseDTO updateRole(
        @PathVariable Long id,
        @Valid @RequestBody RoleRequestDTO dto
    ) {
        return roleService.updateRole(id, dto);
    }

}
