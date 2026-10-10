package com.servicore.web.dtos.response;

public class RolePermissionResponseDTO {
    
    private final Long roleId;
    private final String roleName;
    private final Long permissionId;
    private final String permissionName;

    public RolePermissionResponseDTO(
        Long roleId,
        String roleName,
        Long permissionId,
        String permissionName
    ) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

}
