package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.RolePermissionRequestDTO;
import com.servicore.web.dtos.response.RolePermissionResponseDTO;
import com.servicore.web.services.RolePermissionService;

@RestController 
@RequestMapping("/api/role_permissions")
@CrossOrigin  
public class RolePermissionController {
    
    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping 
    public List<RolePermissionResponseDTO> getAllRolePermissions() {
        return rolePermissionService.getAllRolePermissions();
    }

    @GetMapping("/{roleId}")
    public List<RolePermissionResponseDTO> getAllRolePermissionByRoleId(@PathVariable Long roleId) {
        return rolePermissionService.getAllRolePermissionsByRoleId(roleId);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public RolePermissionResponseDTO assignPermission(@RequestBody RolePermissionRequestDTO dto) {
        return rolePermissionService.assignPermission(dto);
    }

    @DeleteMapping("/{roleId}/{permissionId}")
    public void removePermission(
        @PathVariable Long roleId,
        @PathVariable Long permissionId
    ) {
        rolePermissionService.removePermission(roleId, permissionId);
    } 


}
