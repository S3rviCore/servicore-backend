package com.servicore.web.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RolePermissionRequestDTO {
    
    @NotNull(message = "El ID del rol es requerido.")
    @Positive(message = "El ID del rol debe ser positivo.")
    private Long roleId;

    @NotNull(message = "El ID del permiso es requerido.")
    @Positive(message = "El ID del permiso debe ser positivo")
    private Long permissionId;

    public RolePermissionRequestDTO() {

    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
