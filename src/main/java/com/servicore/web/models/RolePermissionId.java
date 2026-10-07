package com.servicore.web.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable 
public class RolePermissionId implements Serializable {
    
    private Long roleId;
    private Long permissionId;

    public RolePermissionId() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolePermissionId)) return false;

        RolePermissionId that = (RolePermissionId) o;

        return roleId.equals(that.roleId)
                && permissionId.equals(that.permissionId);
    }

    @Override
    public int hashCode() {
        return 31 * roleId.hashCode() + permissionId.hashCode();
    }

}